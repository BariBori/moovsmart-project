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

  loggedIn: EventEmitter<User>;
  loggedOut: EventEmitter<void>;

  constructor(private http: HttpClient) {
    this.loggedIn = new EventEmitter<User>();
    this.loggedOut = new EventEmitter<void>();
  }


  logOut: Observable<void> = this.http.get<void>(this.BASE_URL + '/api/users/logout')
    .pipe(
      tap(() => {
        localStorage.setItem('authnenticated', 'false');
        this.loggedOut.emit();
        console.log('User succesfully logged out');
      })
    );

  authenticate = (credentials: Credentials) => this.http.post(
    this.BASE_URL + '/api/users/authenticate', '',
    { headers: this.getAuthenticationHeaders(credentials) })
    .pipe(
      tap((user: User) => {
        localStorage.setItem('authnenticated', 'true');
        this.loggedIn.emit(user);
        console.log(`User '${user.userName}' succesfully logged in`);
      }),
    )

  getAuthenticationHeaders = (credentials: Credentials) => new HttpHeaders({
    authorization: 'basic ' + btoa(credentials.email + ':' + credentials.password)
  })
}
