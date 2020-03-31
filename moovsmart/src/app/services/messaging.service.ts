import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { tap, map } from 'rxjs/operators';
import { MessageModel } from '../models/messaging/MessageModel';
import { TopicModel } from '../models/messaging/TopicModel';
import { ChatModel } from '../models/messaging/ChatModel';

@Injectable({
  providedIn: 'root'
})
export class MessagingService {
  private BASE_URL: string = environment.BASE_URL + '/api/messages';
  public beginDirectMessaging: (advertId: number) => Observable<number>;
  public sendDirectMessage: (message: string, advertId: number) => Observable<MessageModel>;
  public fetchConversation: (advertId: number) => Observable<ChatModel>;
  public fetchMyTopics: Observable<TopicModel[]>;
  public getUnread: Observable<number>;
  private sumUnread = (topics: TopicModel[]): number => topics.map(topic => topic.unread).reduce((total, unread) => total + unread, 0);

  constructor(
    private http: HttpClient,
  ) {
    this.beginDirectMessaging = (advertId: number) =>
      this.http.post<number>(this.BASE_URL + `/direct/${advertId}`, '')
        .pipe(tap(console.log, console.error));

    this.sendDirectMessage = (message: string, advertId: number) =>
      this.http.put<MessageModel>(this.BASE_URL + `/topic/${advertId}`, message)
        .pipe(tap(console.log, console.error));

    this.fetchConversation = (advertId: number) => this.http.get<ChatModel>(this.BASE_URL + `/topic/${advertId}`);

    this.fetchMyTopics = this.http.get<TopicModel[]>(this.BASE_URL + '/my-topics');
    this.getUnread = this.fetchMyTopics.pipe(map(this.sumUnread));
  }
}
