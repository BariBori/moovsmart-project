import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs/operators';
import { TopicModel } from '../models/messaging/TopicModel';

@Injectable({
  providedIn: 'root'
})
export class MessagingService {

  private BASE_URL: string = environment.BASE_URL + '/api/topics'
  public subscribeToTopic: (advertId: number) => Observable<number>;
  public sendMessage: (message: string, topicId: number) => Observable<void>;
  public fetchTopic: (topicId: number) => Observable<TopicModel>;
  public fetcchAllTopics: Observable<TopicModel[]>;
  constructor(
    private http: HttpClient,
  ) {
    this.subscribeToTopic = (id: number) =>
      this.http.post<number>(this.BASE_URL + '/subscribe', id)
        .pipe(tap(console.log, console.error));

    this.sendMessage = (message: string, topicId: number) =>
      this.http.post<void>(this.BASE_URL + `/${topicId}`, message)
        .pipe(tap(console.log, console.error));

    this.fetchTopic = (topicId: number): Observable<TopicModel> =>
      this.http.get<TopicModel>(this.BASE_URL + `/${topicId}`);

    this.fetcchAllTopics = this.http.get<TopicModel[]>(this.BASE_URL + '/my-topics');
  }
}
