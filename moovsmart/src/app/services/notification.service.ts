import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { environment } from 'src/environments/environment';
import { ReplaySubject } from 'rxjs';
import { UserService } from './user.service';
import { tap } from 'rxjs/operators';

interface NotificationModel {
  unread: number;
}

@Injectable({
  providedIn: 'root'
})

export class NotificationService {
  private socket;
  private stompClient;
  public onNotification: ReplaySubject<NotificationModel> = new ReplaySubject<NotificationModel>(1);

  constructor(private usrService: UserService) {
    this.socket = new SockJS(environment.BASE_URL + '/secured/notifications');
    this.stompClient = Stomp.over(this.socket);
    this.usrService.loggedIn
      .subscribe(loggedin => loggedin
        ?
        this.usrService.getCurrentUser.subscribe(usr =>
          this.stompClient.connect({}, connection => {
            this.stompClient.subscribe('/secured/user/queue/notify',
              (msg => this.onNotification.next(JSON.parse(msg.body)))
            );
          }))
        : this.stompClient.disconnect()
      );
  }
}
