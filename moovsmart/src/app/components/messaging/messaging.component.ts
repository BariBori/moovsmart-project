import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MessagingService } from 'src/app/services/messaging.service';
import { TopicMap } from 'src/app/models/messaging/TopicMap';
import { MessageModel } from 'src/app/models/messaging/MessageModel';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/error/User';
import { tap } from 'rxjs/operators';
import { TopicModel } from 'src/app/models/messaging/TopicModel';
import { Router } from '@angular/router';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-messaging',
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.css']
})
export class MessagingComponent implements OnInit {

  faPaperPlane = faPaperPlane;

  currentUserName: string;
  message: FormControl;
  topics: TopicMap;
  messages: MessageModel[];

  activeTopic: {
    id: number;
    topic: TopicModel;
  };


  constructor(
    private msgservice: MessagingService,
    private userService: UserService,
    private router: Router
  ) {
    this.message = new FormControl('', Validators.required);
    this.activeTopic = {
      id: null,
      topic: null
    };
  }
  send() {
    const id = this.activeTopic.id;
    const message = this.message.value;
    this.msgservice.sendMessage(message, id)
      .subscribe(response => {
        this.topics[id].messages.push(response);
        this.message.setValue('');
      },
        err => console.error(err));

  }

  ngOnInit(): void {
    if (!this.userService.isLoggedIn()) {
      this.router.navigate(['/user-login']);
    }
    this.msgservice.fetchAllTopics.subscribe(
      response => {
        this.activeTopic.id = Number(Object.keys(response)[0]);
        this.activeTopic.topic = response[this.activeTopic.id];
        this.topics = response;
      },
      err => console.error(err));

    this.userService.getCurrentUser.subscribe(
      response => this.currentUserName = response.userName
    );
  }

  format(dateString: string): string {
    return new Date(dateString).toLocaleString();
  }

  setActiveTopic(topicId: number, topic: TopicModel): void {
    this.activeTopic.id = topicId;
    this.activeTopic.topic = topic;
  }
}
