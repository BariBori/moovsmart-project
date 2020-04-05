import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { MessagingService, TopicMap } from 'src/app/services/messaging.service';
import { MessageModel } from 'src/app/models/messaging/MessageModel';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { ChatModel } from 'src/app/models/messaging/ChatModel';
import { tap, map, flatMap, filter, pluck } from 'rxjs/operators';
import { NotificationService } from 'src/app/services/notification.service';
import { Observable, ReplaySubject, merge, BehaviorSubject, combineLatest } from 'rxjs';

@Component({
  selector: 'app-messaging',
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.css']
})
export class MessagingComponent implements OnInit {

  faPaperPlane = faPaperPlane;
  faTrash = faTrash;

  currentUserName: Observable<string>;
  form = new FormGroup({
    message: new FormControl('', Validators.required),
  });
  topics: Observable<TopicMap>;
  messages: MessageModel[];
  mouseOverId: number = null;
  unsubscribe: (chatId: number) => void;
  activeTopic = new BehaviorSubject<ChatModel>(null);
  activeTopicId = new ReplaySubject<number>(1);



  constructor(
    private msgservice: MessagingService,
    private userService: UserService,
    private notificationService: NotificationService,
    private router: Router
  ) {
    this.currentUserName = userService.getCurrentUser.pipe(map(user => user.userName));
    this.topics = this.msgservice.myTopics;
    this.unsubscribe = (chatId: number) => this.msgservice.unsubscribe(chatId)
      .pipe(tap(() => {
        this.activeTopic.next(null);
      }))
      .subscribe();
  }

  ngOnInit(): void {
    if (!this.userService.isLoggedIn()) {
      this.router.navigate(['/user-login']);
    }

    this.activeTopic.subscribe(
      topic => topic === null
        ? this.form.get('message').disable()
        : this.form.get('message').enable()
    );

    const unreadId: Observable<number> = this.notificationService.onNotification
      .pipe(
        filter(notification => notification.incoming),
        pluck('incoming'),
      );


    merge(
      this.activeTopicId,
      combineLatest([
        this.activeTopicId,
        unreadId
      ]).pipe(
        filter(([active, unread]) => active === unread),
        map(([active, unread]) => active)
      )
    )
      .pipe(flatMap(id => this.msgservice.fetchConversation(id)))
      .subscribe(chat => this.activeTopic.next(chat));

  }

  send() {
    this.activeTopicId.pipe(
      flatMap(id =>
        this.msgservice.sendDirectMessage(this.form.get('message').value, id)),
      tap(() => this.form.get('message').setValue('')),
    )
      .subscribe(chat => this.activeTopic.next(chat));

  }

  format(dateString: string): string {
    return new Date(dateString).toLocaleString();
  }

}
