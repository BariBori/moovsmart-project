import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MessagingService } from 'src/app/services/messaging.service';
import { TopicMap } from 'src/app/models/messaging/TopicMap';
import { MessageModel } from 'src/app/models/messaging/MessageModel';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/error/User';
import { tap } from 'rxjs/operators';
import { TopicModel } from 'src/app/models/messaging/TopicModel';

@Component({
  selector: 'app-messaging',
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.css']
})
export class MessagingComponent implements OnInit {

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
    private userService: UserService
  ) {
    this.message = new FormControl('', Validators.required);
    this.activeTopic = {
      id: null,
      topic: null
    };
  }
  send() {
    this.msgservice.sendMessage(this.message.value, this.activeTopic.id)
      .subscribe(success => this.message.setValue(''),
        err => console.error(err));

  }

  ngOnInit(): void {
    this.msgservice.fetchAllTopics.subscribe(
      response => this.topics = response,
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
