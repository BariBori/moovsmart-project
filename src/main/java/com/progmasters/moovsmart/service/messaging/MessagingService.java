package com.progmasters.moovsmart.service.messaging;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.messaging.Chat;
import com.progmasters.moovsmart.domain.messaging.Message;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserIdentifier;
import com.progmasters.moovsmart.dto.messaging.TopicDto;
import com.progmasters.moovsmart.repository.AdvertRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import com.progmasters.moovsmart.repository.messaging.ChatRepository;
import com.progmasters.moovsmart.repository.messaging.ChatViewRepository;
import com.progmasters.moovsmart.repository.messaging.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class MessagingService {
    private final UserRepository userRepository;
    private final AdvertRepository advertRepository;
    private final ChatRepository chatRepository;
    private final ChatViewRepository viewRepository;
    private final MessageRepository messageRepository;

    public MessagingService(
            MessageRepository messageRepository,
            UserRepository userRepository,
            AdvertRepository advertRepository,
            ChatViewRepository chatViewRepository,
            ChatRepository chatRepository
    ) {
        this.userRepository = userRepository;
        this.advertRepository = advertRepository;
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.viewRepository = chatViewRepository;
    }

    public boolean isSubscribed(UserIdentifier user, Long advertId) {
        return viewRepository.streamAllByUser(userRepository.get(user))
                .anyMatch(view -> view.getConversation().getAdvert().getId() == advertId);
    }

    public Message saveDirectMessage(UserIdentifier sender, String message, Long advertId) {
        User user = userRepository.get(sender);
        Chat chat = chatRepository.getByUserAndAdvert(user, advertId)
                .orElseThrow(() ->
                        new EntityNotFoundException("that topic doesn't exist"));
        Chat.View partnerView = viewRepository.findOneByPartnerAndConversation(user, chat)
                .orElseThrow(() ->
                        new EntityNotFoundException("that topic doesn't exist"));

        viewRepository.save(partnerView.addUnread());
        return messageRepository.save(new Message(user, chat, message));
    }

    public List<TopicDto> getTopicsByUser(UserIdentifier user) {
        return viewRepository.streamAllByUser(userRepository.get(user))
                .map(TopicDto::fromView)
                .collect(Collectors.toList());
    }

    public void enquire(UserIdentifier enquirer, Long advertId) {
        if (renderUserViewForChat(enquirer, advertId).isEmpty()) {
            initiateChat(enquirer, advertId);
        }
    }

    public void deleteChatView(UserIdentifier user, Long advertId) {
        viewRepository.findOneByUserAndConversation_Advert_Id(userRepository.get(user), advertId)
                .ifPresent(view -> {
                    viewRepository.delete(view);
                    if (viewRepository.findAllByConversation(view.getConversation()).isEmpty()) {
                        chatRepository.delete(view.getConversation());
                    }
                });
    }

    public Optional<Chat.View> renderUserViewForChat(UserIdentifier usr, Long advertId) {
        User user = userRepository.get(usr);
        return chatRepository.getByUserAndAdvert(user, advertId)
                .map(chat ->
                        viewRepository
                                .findOneByUserAndConversation(user, chat)
                                .map(view -> viewRepository.save(view.readAll()))
                                .orElseGet(() ->
                                        viewRepository.save(new Chat.View(user, chat))
                                )
                );
    }

    private void initiateChat(UserIdentifier enquirer, Long advertId) {
        PropertyAdvert advert = advertRepository.findOneById(advertId)
                .orElseThrow(
                        () -> new EntityNotFoundException("that advert doesn't exist")
                );
        User user = userRepository.get(enquirer);
        Chat directMessaging = chatRepository.save(new Chat(user, advert));
        viewRepository.save(new Chat.View(user, directMessaging));
        viewRepository.save(new Chat.View(advert.getUser(), directMessaging));
    }
}
