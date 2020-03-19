import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, Output, EventEmitter } from '@angular/core';
import { Credentials } from '../models/Credentials';
import { Observable } from 'rxjs';
import { tap, map, flatMap } from 'rxjs/operators';
import { flatten } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private BASE_URL = 'http://localhost:8080/api/users';

  public credentials: Credentials;

  @Output() loggedIn: EventEmitter<number>;
  @Output() loggedOut: EventEmitter<void>;

  constructor(private http: HttpClient) {
    this.loggedIn = new EventEmitter<number>();
    this.loggedOut = new EventEmitter<void>();
  }

  private fetchUserId: Observable<number> = this.http.get<number>(this.BASE_URL + '/me')
    .pipe(
      tap(gotId => {
        console.log(`Aquired userId ${gotId}`);
      })
    );

  logOut: Observable<void> = this.http.get<void>(this.BASE_URL + '/logout')
    .pipe(
      tap(success => {
        this.loggedOut.emit();
        console.log(`User with id '${this.userId}' succesfully logged out`);
        this.userId = null;
      })
    );

  authenticate = (credentials: Credentials) => this.http.post(
    this.BASE_URL + '/authenticate', '',
    { headers: this.getAuthenticationHeaders(credentials) })
    .pipe(
      tap((gotId: number) => {
        this.userId = gotId;
        this.loggedIn.emit(this.userId);
        console.log(`User with id '${this.userId}' succesfully logged in`);
      }),
    )

  getAuthenticationHeaders = (credentials: Credentials) => new HttpHeaders({
    authorization: 'basic ' + btoa(credentials.email + ':' + credentials.password)
  })

  public getUserId = (): number => this.userId;

}
