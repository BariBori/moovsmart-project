import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MessagingService } from 'src/app/services/messaging.service';
import { TopicMap } from 'src/app/models/messaging/TopicMap';
import { MessageModel } from 'src/app/models/messaging/MessageModel';

@Component({
  selector: 'app-messaging',
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.css']
})
export class MessagingComponent implements OnInit {

  message: FormControl;
  topics: TopicMap;
  messages: MessageModel[];
  activeTopicId: number;

  constructor(
    private msgservice: MessagingService,
  ) {
    this.message = new FormControl('', Validators.required);
  }
  send() {
    this.msgservice.sendMessage(this.message.value, this.activeTopicId)
      .subscribe(success => this.message.setValue(''),
        err => console.error(err));

  }

  ngOnInit(): void {
    this.msgservice.fetchAllTopics.subscribe(
      response => this.topics = response,
      err => console.error(err));
  }
  setActiveTopic(topicId: number): void {
    this.activeTopicId = topicId;
    console.log(this.activeTopicId);
  }

}
