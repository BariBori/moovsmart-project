import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { environment } from 'src/environments/environment';
import { ReplaySubject } from 'rxjs';
import { UserService } from './user.service';
import { tap, map, flatMap } from 'rxjs/operators';
interface Notification {
  [key: string]: any;
}
@Injectable({
  providedIn: 'root'
})

export class NotificationService {
  private socket;
  private stompClient;
  public onNotification = new ReplaySubject<Notification>(1);

  constructor(private usrService: UserService) {
    this.socket = new SockJS(environment.BASE_URL + '/secured/notifications');
    this.stompClient = Stomp.over(this.socket);

    this.usrService.loggedIn
      .subscribe(loggedin => loggedin
        ?
        this.usrService.getCurrentUser.subscribe(usr =>
          this.stompClient.connect({}, connection => {
            this.stompClient.subscribe('secured/user/queue/notify' + `-${usr.userName}`,
              (msg => this.onNotification.next(msg))
            );
          }))
        : this.stompClient.disconnect()
      );
  }
}
