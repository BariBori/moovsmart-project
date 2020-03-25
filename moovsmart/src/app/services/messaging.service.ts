import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MessagingService {

  public subscribeToTopic: (advertId: number) => Observable<number>;

  constructor(
    private http: HttpClient,
  ) {
    this.subscribeToTopic = (id: number) => this.http.post<number>(environment.BASE_URL + '/api/topics/subscribe', id)
      .pipe(tap(console.log, console.log));
  }
}
