import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, Output, EventEmitter } from '@angular/core';
import { Credentials } from '../models/Credentials';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { User } from '../models/error/User';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private BASE_URL = environment.BASE_URL;

  @Output() loggedIn: EventEmitter<User>;
  @Output() loggedOut: EventEmitter<null>;

  constructor(private http: HttpClient) {
    this.loggedIn = new EventEmitter<User>();
    this.loggedOut = new EventEmitter<null>();
  }


  logOut: Observable<void> = this.http.get<void>(this.BASE_URL + '/logout')
    .pipe(
      tap(() => {
        this.loggedOut.emit(null);
        console.log('User succesfully logged out');
      })
    );

  authenticate = (credentials: Credentials) => this.http.post(
    this.BASE_URL + '/api/users/authenticate', '',
    { headers: this.getAuthenticationHeaders(credentials) })
    .pipe(
      tap((user: User) => {
        this.loggedIn.emit(user);
        console.log(`User '${user.userName}' succesfully logged in`);
      }),
    )

  getAuthenticationHeaders = (credentials: Credentials) => new HttpHeaders({
    authorization: 'basic ' + btoa(credentials.email + ':' + credentials.password)
  })
}
