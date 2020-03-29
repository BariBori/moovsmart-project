import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MessagingService } from 'src/app/services/messaging.service';
import { MessageModel } from 'src/app/models/messaging/MessageModel';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { ChatModel } from 'src/app/models/messaging/ChatModel';
import { TopicModel } from 'src/app/models/messaging/TopicModel';
import { tap, flatMap } from 'rxjs/operators';

@Component({
  selector: 'app-messaging',
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.css']
})
export class MessagingComponent implements OnInit {

  faPaperPlane = faPaperPlane;

  currentUserName: string;
  message: FormControl;
  topics: TopicModel[];
  messages: MessageModel[];

  activeTopic: {
    advertId: number;
    chat: ChatModel;
  };


  constructor(
    private msgservice: MessagingService,
    private userService: UserService,
    private router: Router
  ) {
    this.message = new FormControl('', Validators.required);
    this.activeTopic = {
      advertId: null,
      chat: null
    };
  }
  send() {
    const id = this.activeTopic.advertId;
    const message = this.message.value;
    this.msgservice.sendDirectMessage(message, id)
      .subscribe(response => {
        this.activeTopic.chat.messages.push(response);
        this.message.setValue('');
      },
        err => console.error(err));

  }

  ngOnInit(): void {
    if (!this.userService.isLoggedIn()) {
      this.router.navigate(['/user-login']);
    }
    this.msgservice.fetchMyTopics
      .pipe(
        tap(topics => this.activeTopic.advertId = topics[0].advertId),
        tap(topics => this.topics = topics),
        flatMap(id => this.msgservice.fetchConversation(id[0].advertId))
      )
      .subscribe(
        chat => this.activeTopic.chat = chat,
        err => console.error(err)
      );

    this.userService.getCurrentUser.subscribe(
      response => this.currentUserName = response.userName
    );
  }

  format(dateString: string): string {
    return new Date(dateString).toLocaleString();
  }

  setActiveTopic(advertId: number, topic: TopicModel): void {
    this.msgservice.fetchConversation(advertId).subscribe(
      conversation => {
        this.activeTopic.advertId = conversation.advertId;
        this.activeTopic.chat = conversation;
      },
      console.error
    );
  }
}
