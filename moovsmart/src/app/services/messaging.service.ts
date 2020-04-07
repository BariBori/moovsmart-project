import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, ReplaySubject, merge } from 'rxjs';
import { environment } from 'src/environments/environment';
import { tap, flatMap, filter } from 'rxjs/operators';
import { TopicModel } from '../models/messaging/TopicModel';
import { ChatModel } from '../models/messaging/ChatModel';
import { NotificationService } from './notification.service';
import { UserService } from './user.service';

export interface TopicMap {
  [id: number]: TopicModel;
}
@Injectable({
  providedIn: 'root'
})
export class MessagingService {
  private BASE_URL: string = environment.BASE_URL + '/api/messages';
  public beginDirectMessaging: (advertId: number) => Observable<TopicMap>;
  public sendDirectMessage: (message: string, advertId: number) => Observable<ChatModel>;
  public fetchConversation: (chatId: number) => Observable<ChatModel>;
  public unsubscribe: (advertId: number) => Observable<TopicMap>;
  public myTopics = new ReplaySubject<TopicMap>(1);
  private refreshTopics: () => Observable<TopicMap>;


  constructor(
    private http: HttpClient,
    private notificationService: NotificationService,
    private userService: UserService
  ) {
    this.beginDirectMessaging = (advertId: number) =>
      this.http.post<TopicMap>(this.BASE_URL + `/direct/${advertId}`, '').pipe(
        tap(
          response => this.myTopics.next(response),
          console.error,
          () => console.log(`Initialized chat for advert id #${advertId}`)
        ));

    this.sendDirectMessage = (message: string, chatId: number) =>
      this.http.put<ChatModel>(this.BASE_URL + `/topic/${chatId}`, message).pipe(
        tap(
          chat => this.fetchConversation(chat.id),
          console.error,
          () => console.log(`DM sent to chat #${chatId}`)
        ));

    this.fetchConversation = (chatId: number) =>
      this.http.get<ChatModel>(this.BASE_URL + `/topic/${chatId}`).pipe(
        tap(
          () => this.refreshTopics().subscribe(),
          console.error,
          () => console.log(`Converation #${chatId} updated`)
        ));

    this.unsubscribe = (chatId: number) =>
      this.http.post<TopicMap>(this.BASE_URL + `/unsubscribe/${chatId}`, '').pipe(
        tap(
          response => this.myTopics.next(response),
          console.error,
          () => console.log(`Unsubscribed from conversation #${chatId}`)
        ));

    this.refreshTopics = () => this.http.get<TopicMap>(this.BASE_URL + '/my-topics').pipe(
      tap(
        topics => this.myTopics.next(topics),
        console.error,
        () => console.log('myTopics succesfully updated')
      )
    );

    merge(
      this.userService.loggedIn.pipe(filter(loggedIn => loggedIn === true)),
      this.notificationService.onNotification.pipe(
        filter(notification =>
          notification.incoming
          || notification.new))
    )
      .pipe(flatMap(() => this.refreshTopics()))
      .subscribe();
  }
}
