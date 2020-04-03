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
import org.springframework.messaging.simp.SimpMessageSendingOperations;
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
    private final SimpMessageSendingOperations msgOps;

    public MessagingService(
            MessageRepository messageRepository,
            UserRepository userRepository,
            AdvertRepository advertRepository,
            ChatViewRepository chatViewRepository,
            ChatRepository chatRepository,
            SimpMessageSendingOperations msgOps) {
        this.userRepository = userRepository;
        this.advertRepository = advertRepository;
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.viewRepository = chatViewRepository;
        this.msgOps = msgOps;
    }

    public boolean isSubscribed(UserIdentifier user, Long chatId) {
        return viewRepository.findOneByUser_IdAndConversation_Id(
                user.getId(),
                chatId)
                .isPresent();
    }

    public Message saveDirectMessage(UserIdentifier sender, String message, Long chatId) {
        viewRepository.findOneByPartner_IdAndConversation_Id(sender.getId(),chatId)
                .map(view -> viewRepository.save(view.addUnread()));
        return messageRepository.save(new Message(
                userRepository.get(sender),
                chatRepository.getOne(chatId),
                message));

//        msgOps.convertAndSendToUser(
//                partnerView.getUser().getUserName(),
//                "/queue/notify",
//                "unread");
    }

    public List<TopicDto> getTopicsByUser(UserIdentifier user) {
        return viewRepository.streamAllByUser(userRepository.get(user))
                .map(TopicDto::fromView)
                .collect(Collectors.toList());
    }

    public void enquire(UserIdentifier enquirer, Long advertId) {
        chatRepository.findOneByEnquirer_IdAndAdvert_Id(enquirer.getId(), advertId)
                .ifPresentOrElse(
                        chat -> renderUserViewForChat(enquirer, chat.getId())
                                .or(() -> reopenChat(enquirer, advertId)),
                        () -> initiateChat(enquirer, advertId)
                );

    }

    public void deleteChatView(UserIdentifier user, Long chatId) {
        viewRepository.findOneByUser_IdAndConversation_Id(user.getId(), chatId)
                .ifPresent(view -> {
                    viewRepository.delete(view);
                    if (viewRepository.findAllByConversation_Id(chatId).isEmpty()) {
                        messageRepository.deleteAllByConversation_Id(chatId);
                        chatRepository.deleteById(chatId);
                    }
                });
    }

    private Optional<Chat.View> reopenChat(UserIdentifier enquirer, Long advertId) {
        return chatRepository.findOneByEnquirer_IdAndAdvert_Id(enquirer.getId(), advertId)
                .map(chat ->
                        viewRepository.save(new Chat.View(
                                userRepository.get(enquirer),
                                chat
                        ))
                );
    }

    public Optional<Chat.View> renderUserViewForChat(UserIdentifier viewer, Long chatId) {
        return viewRepository.findOneByUser_IdAndConversation_Id(viewer.getId(), chatId)
                .map(view -> viewRepository.save(view.readAll()));
    }

    private void initiateChat(UserIdentifier enquirer, Long advertId) {
        PropertyAdvert advert = advertRepository.findOneById(advertId)
                .orElseThrow(() -> new EntityNotFoundException("that advert doesn't exist"));
        User user = userRepository.get(enquirer);
        Chat chat = chatRepository.save(new Chat(user, advert));
        viewRepository.save(new Chat.View(user, chat));
        viewRepository.save(new Chat.View(advert.getUser(), chat));
    }
}
