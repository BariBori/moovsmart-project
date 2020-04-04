import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MessagingService, TopicMap } from 'src/app/services/messaging.service';
import { MessageModel } from 'src/app/models/messaging/MessageModel';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { ChatModel } from 'src/app/models/messaging/ChatModel';
import { TopicModel } from 'src/app/models/messaging/TopicModel';
import { tap, map, flatMap } from 'rxjs/operators';
import { MatBadgeModule } from '@angular/material/badge';
import { MatTooltipModule } from '@angular/material/tooltip';
import { NotificationService } from 'src/app/services/notification.service';
import { iif, of } from 'rxjs';

@Component({
  selector: 'app-messaging',
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.css']
})
export class MessagingComponent implements OnInit {

  faPaperPlane = faPaperPlane;
  faTrash = faTrash;

  currentUserName: string;
  message = new FormControl('', Validators.required);
  topics: TopicMap;
  messages: MessageModel[];
  mouseOverId: number = null;
  setActiveTopic: (topic: TopicModel) => void;
  unsubscribe: (chatId: number) => void;
  activeTopic: ChatModel | null = null;
  refreshTopics: () => void;


  constructor(
    private msgservice: MessagingService,
    private notificationService: NotificationService,
    private userService: UserService,
    private router: Router
  ) {
    this.message.disable();
    this.refreshTopics = () => void this.msgservice.fetchMyTopics.subscribe(
      topics => this.topics = topics
    );
    this.unsubscribe = (chatId: number) => this.msgservice.unsubscribe(chatId)
      .subscribe(() => {
        this.refreshTopics();
        if (this.activeTopic?.id === chatId) {
          this.activeTopic = null;
          this.message.disable();
        }
      });


    this.setActiveTopic = (topic: TopicModel) => void this.msgservice
      .fetchConversation(topic.chatId).pipe(
        tap(chat => this.topics[chat.id].unread = 0))
      .subscribe(
        chat => {
          this.activeTopic = chat;
          this.message.enable();
        },
        console.error
      );
  }

  send() {
    this.msgservice.sendDirectMessage(this.message.value, this.activeTopic.id)
      .subscribe(response => {
        this.activeTopic.messages.push(response);
        this.message.setValue('');
      });
  }

  ngOnInit(): void {
    if (!this.userService.isLoggedIn()) {
      this.router.navigate(['/user-login']);
    }
    this.refreshTopics();
    this.userService.getCurrentUser.subscribe(
      response => this.currentUserName = response.userName
    );

    this.notificationService.onNotification.pipe(
      tap(() => this.refreshTopics()),
      flatMap(notification =>
        iif(() => notification.unread === this.activeTopic?.id,
          this.msgservice.fetchConversation(notification.unread)
            .pipe(tap(() => this.topics[this.activeTopic.id].unread = 0)),
          of(this.activeTopic)
        )))
      .subscribe(chat => this.activeTopic = chat);

  }

  format(dateString: string): string {
    return new Date(dateString).toLocaleString();
  }

}
