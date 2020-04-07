import { TestBed } from '@angular/core/testing';

import { MessagingService } from './messaging.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { NotificationService } from './notification.service';
import { UserService } from './user.service';
import { environment } from 'src/environments/environment';
import { TopicModel } from '../models/messaging/TopicModel';
import { ChatModel } from '../models/messaging/ChatModel';
import { BehaviorSubject } from 'rxjs';

describe('MessagingService', () => {
    let service: MessagingService;
    let controller: HttpTestingController;
    const BASE_URL = environment.BASE_URL + '/api/messages';

    const fakeTopic: TopicModel = {
        chatId: 1,
        title: 'chat',
        partner: 'otherUser',
        unread: 0
    };

    const fakeChat: ChatModel = {
        id: 1,
        messages: [{
            sender: 'user',
            text: 'msg',
            sentAt: Date.now().toString()
        }]
    };
    const userServiceMock = {
        loggedIn: new BehaviorSubject<boolean>(true)
    };

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [
                NotificationService,
                { provide: UserService, useValue: userServiceMock }
            ]
        });
        controller = TestBed.inject(HttpTestingController);
        service = TestBed.inject(MessagingService);
    });


    it('should init messaging', () => {
        service.beginDirectMessaging(0).subscribe();
        controller.expectOne({ method: 'POST', url: BASE_URL + '/direct/0' })
            .flush({ 1: fakeTopic });
        service.myTopics.subscribe(topic => expect(topic).toEqual(
            { 1: fakeTopic }
        ));
    });

    it('should send messages', () => {
        service.sendDirectMessage('msg', 1).subscribe(chat =>
            expect(chat).toEqual(fakeChat));
        controller.expectOne({ method: 'PUT', url: BASE_URL + '/topic/1' })
            .flush(fakeChat);
    });

    it('should fetch chats', () => {
        service.fetchConversation(1).subscribe(
            chat => expect(chat).toEqual(fakeChat)
        );
        controller.expectOne({ method: 'GET', url: BASE_URL + '/my-topics' })
            .flush({ 1: fakeTopic });
        controller.expectOne({ method: 'GET', url: BASE_URL + '/topic/1' })
            .flush(fakeChat);
        service.myTopics.subscribe(
            topics => expect(topics).toEqual({ 1: fakeTopic })
        );
    });

    it('should unsubscribe from chats', () => {
        expect(service).toBeTruthy();
    });

    it('sould refresh topics', () => {
        expect(service).toBeTruthy();
    });

    afterAll(() => {
        controller.expectOne({ method: 'GET', url: BASE_URL + '/my-topics' })
            .flush({ 1: fakeTopic });
        controller.verify();
    });
});
