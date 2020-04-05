package com.progmasters.moovsmart.service.messaging;

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

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
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
        Optional<Chat.View> maybeView =
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

        maybeView.ifPresent(view -> notificationService.notifyUser(
                view.getPartner().getUserName(),
                Map.entry("incoming", view.getConversation().getId())
        ));

        return maybeView;
    }


    public Map<Long, TopicDto> getTopicsByUserIdentifier(UserIdentifier user) {
        return getTopicsByUser(userRepository.get(user));
    }

    public Map<Long, TopicDto> enquire(UserIdentifier enquirer, Long advertId) {
        Optional<Chat.View> maybeView =
                chatRepository.findOneByEnquirer_IdAndAdvert_Id(enquirer.getId(), advertId)
                        .flatMap(chat ->
                                viewRepository.findOneByUser_IdAndConversation_Id(enquirer.getId(), chat.getId()))
                        .or(() -> reopenChat(enquirer, advertId));
        return maybeView
                .map(view -> getTopicsByUserIdentifier(enquirer))
                .orElseGet(() -> initiateChat(enquirer, advertId));
    }

    public Map<Long, TopicDto> deleteChatView(UserIdentifier user, Long chatId) {
        viewRepository.findOneByUser_IdAndConversation_Id(user.getId(), chatId)
                .ifPresent(viewRepository::delete);
        if (viewRepository.findAllByConversation_Id(chatId).isEmpty()) {
            messageRepository.deleteAllByConversation_Id(chatId);
            chatRepository.deleteById(chatId);
        }
        return getTopicsByUserIdentifier(user);
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
                        Function.identity()));
    }

    private Optional<Chat.View> reopenChat(UserIdentifier enquirer, Long advertId) {
        return chatRepository.findOneByEnquirer_IdAndAdvert_Id(enquirer.getId(), advertId)
                .map(chat -> viewRepository.save(new Chat.View(
                        userRepository.get(enquirer),
                        chat)));
    }

    private Map<Long, TopicDto> initiateChat(UserIdentifier enquirer, Long advertId) {
        User user = userRepository.get(enquirer);
        advertRepository.findOneById(advertId)
                .map(advert -> chatRepository.save(new Chat(
                        user,
                        advert)
                ))
                .map(chat -> viewRepository.save(new Chat.View(user, chat)))
                .map(view -> viewRepository.save(new Chat.View(view.getPartner(), view.getConversation())))
                .ifPresent(partnerView -> notificationService
                        .notifyUser(
                                partnerView.getUser().getUserName(),
                                Map.entry("new", partnerView.getConversation().getId())
                        )
                );
        return getTopicsByUserIdentifier(enquirer);
    }
}
