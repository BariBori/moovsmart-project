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
  public unsubscribe: (advertId: number) => Observable<void>;
  public fetchMyTopics: Observable<TopicModel[]>;

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

    this.unsubscribe = (advertId: number) => this.http.post<void>(this.BASE_URL + `/unsubscribe/${advertId}`, '');
  }
}
