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
import java.util.Map;
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
    private final NotificationService notificationService;

    public MessagingService(
            MessageRepository messageRepository,
            UserRepository userRepository,
            AdvertRepository advertRepository,
            ChatViewRepository chatViewRepository,
            ChatRepository chatRepository,
            NotificationService notificationService) {
        this.userRepository = userRepository;
        this.advertRepository = advertRepository;
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.viewRepository = chatViewRepository;
        this.notificationService = notificationService;
    }

    public Optional<Chat.View> saveDirectMessage(UserIdentifier sender, String message, Long chatId) {
        Optional<Chat.View> result =
                viewRepository.findOneByPartner_IdAndConversation_Id(sender.getId(), chatId)
                        .map(view -> viewRepository.save(view.addUnread()))
                        .or(() -> viewRepository.findOneByUser_IdAndConversation_Id(sender.getId(), chatId))
                        .map(Chat.View::getConversation)
                        .map(chat -> messageRepository.save(new Message(
                                userRepository.get(sender),
                                chat,
                                message)
                        ))
                        .flatMap(msg -> viewRepository.findOneByUser_IdAndConversation_Id(sender.getId(), chatId));

        result.ifPresent(view -> notificationService.notifyUser(
                view.getPartner().getUserName(),
                Map.entry("incoming", view.getConversation().getId())
        ));

        return result;
    }


    public Map<Long, TopicDto> getTopicsByUserIdentifier(UserIdentifier user) {
        return getTopicsByUser(userRepository.get(user));
    }

    public Map<Long, TopicDto> enquire(UserIdentifier enquirer, Long advertId) {
        return chatRepository.findOneByEnquirer_IdAndAdvert_Id(enquirer.getId(), advertId)
                .flatMap(chat ->
                        viewRepository.findOneByUser_IdAndConversation_Id(enquirer.getId(), chat.getId())
                )
                .or(() -> reopenChat(enquirer, advertId))
                .map(view -> getTopicsByUserIdentifier(enquirer))
                .orElseGet(() -> initiateChat(enquirer, advertId));
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
        notificationService.notifyUser(user.getUsername(), Map.entry("unsubscribed", chatId));
    }

    public Optional<Chat.View> renderUserViewForChat(UserIdentifier viewer, Long chatId) {
        return viewRepository.findOneByUser_IdAndConversation_Id(viewer.getId(), chatId)
                .map(view -> {
                    notificationService.notifyUser(viewer.getUsername(), Map.entry("viewed", chatId));
                    notificationService.notifyUser(view.getPartner().getUserName(), Map.entry("read", chatId));
                    return viewRepository.save(view.readAll());
                });
    }

    private Map<Long, TopicDto> getTopicsByUser(User user) {
        return viewRepository.streamAllByUser(user)
                .map(TopicDto::fromView)
                .collect(Collectors.toMap(
                        TopicDto::getChatId,
                        dto -> dto
                ));
    }

    private Optional<Chat.View> reopenChat(UserIdentifier enquirer, Long advertId) {
        return chatRepository.findOneByEnquirer_IdAndAdvert_Id(enquirer.getId(), advertId)
                .map(chat -> viewRepository.save(new Chat.View(
                        userRepository.get(enquirer),
                        chat)));
    }

    private Map<Long, TopicDto> initiateChat(UserIdentifier enquirer, Long advertId) {
        PropertyAdvert advert = advertRepository.findOneById(advertId)
                .orElseThrow(() -> new EntityNotFoundException("that advert doesn't exist"));
        User user = userRepository.get(enquirer);
        Chat chat = chatRepository.save(new Chat(user, advert));
        viewRepository.save(new Chat.View(user, chat));
        viewRepository.save(new Chat.View(advert.getUser(), chat));
        notificationService.notifyUser(advert.getUser().getUserName(), Map.entry("new", chat.getId()));
        return getTopicsByUserIdentifier(enquirer);
    }
}
