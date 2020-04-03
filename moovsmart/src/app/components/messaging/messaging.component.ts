import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MessagingService } from 'src/app/services/messaging.service';
import { MessageModel } from 'src/app/models/messaging/MessageModel';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { ChatModel } from 'src/app/models/messaging/ChatModel';
import { TopicModel } from 'src/app/models/messaging/TopicModel';
import { tap, map } from 'rxjs/operators';
import { MatBadgeModule } from '@angular/material/badge';
import { MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-messaging',
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.css']
})
export class MessagingComponent implements OnInit {

  faPaperPlane = faPaperPlane;
  faTrash = faTrash;

  currentUserName: string;
  message: FormControl;
  topics: TopicModel[];
  messages: MessageModel[];
  mouseOverId: number = null;
  setActiveTopic: (topic: TopicModel) => void;
  unsubscribe: (topic: TopicModel) => void;
  activeTopic: {
    chatId: number;
    chat: ChatModel;
  };
  refreshTopics: () => void;


  constructor(
    private msgservice: MessagingService,
    private userService: UserService,
    private router: Router
  ) {
    this.activeTopic = null;
    this.message = new FormControl('', Validators.required);
    this.message.disable();
    this.refreshTopics = () => void this.msgservice.fetchMyTopics.subscribe(
      topics => this.topics = topics
    );
    this.unsubscribe = (topic: TopicModel) => this.msgservice.unsubscribe(topic.chatId)
      .subscribe(() => {
        this.refreshTopics();
        if (this.activeTopic?.chatId === topic.chatId) {
          this.activeTopic = null;
          this.message.disable();
        }
      },
        console.error);


    this.setActiveTopic = (topic: TopicModel) => void this.msgservice.fetchConversation(topic.chatId)
      .pipe(tap(
        () => this.topics = this.topics
          .map(t => t.chatId === topic.chatId
            ? {
              chatId: t.chatId,
              title: t.title,
              partner: t.partner,
              unread: 0
            }
            : t))
      )
      .subscribe(
        conversation => {
          this.activeTopic = {
            chatId: topic.chatId,
            chat: conversation,
          };
          this.message.enable();
        },
        console.error
      );
  }
  send() {
    const id = this.activeTopic.chatId;
    const message = this.message.value;
    this.msgservice.sendDirectMessage(message, id)
      .subscribe(response => {
        this.activeTopic.chat.messages.push(response);
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
  }

  format(dateString: string): string {
    return new Date(dateString).toLocaleString();
  }

}
