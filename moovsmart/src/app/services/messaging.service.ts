import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from './user.service';
import { TopicFormData } from '../models/messaging/TopicFormData';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MessagingService {

  public openTopic: (topic: TopicFormData) => Observable<void>;

  constructor(
    private http: HttpClient,
    private service: UserService
  ) {
    this.openTopic = (topic: TopicFormData) => this.http.post<void>(environment.BASE_URL + '/api/topics', topic)
      .pipe(tap(console.log, console.log));
  }
}
