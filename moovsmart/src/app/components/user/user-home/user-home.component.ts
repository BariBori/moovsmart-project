import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/error/User';
import { faCity, faEnvelope, faUser } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { MatBadgeModule } from '@angular/material/badge';
import { MessagingService, TopicMap } from 'src/app/services/messaging.service';
import { TopicModel } from 'src/app/models/messaging/TopicModel';
import { NotificationService } from 'src/app/services/notification.service';


@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  faCity = faCity;
  faEnvelope = faEnvelope;
  faUser = faUser;
  public unread = 0;
  private sumUnread: (topics: TopicMap) => void;

  constructor(
    private service: UserService,
    private msgService: MessagingService,
    private notificationService: NotificationService,
    private router: Router) {
    this.sumUnread = (topics) => this.unread = Object.values(topics)
      .map(topic => topic.unread)
      .reduce((total, unread) => total + unread, 0);
  }

  user: User;
  ngOnInit(): void {
    if (this.service.isLoggedIn()) {
      this.service.getCurrentUser.subscribe(gotUser => this.user = gotUser);
    } else {
      this.router.navigate(['user-login']);
    }
    this.msgService.fetchMyTopics.subscribe(this.sumUnread);
  }
}
