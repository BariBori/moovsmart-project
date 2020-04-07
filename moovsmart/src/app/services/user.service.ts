import { Injectable } from '@angular/core';
import { UserFormDataModel } from '../models/userFormData.model';
import { Observable, ReplaySubject, BehaviorSubject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/error/User';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Credentials } from '../models/Credentials';

const session = {
  authenticated: sessionStorage.getItem('authenticated')
    ? true
    : false,
  authenticate() {
    sessionStorage.setItem('authenticated', 'true');
    this.authenticated = true;
  },
  inauthenticate() {
    sessionStorage.removeItem('authenticated');
    this.authenticated = false;
  }
};
@Injectable({
  providedIn: 'root'
})
export class UserService {


  private BASE_URL = environment.BASE_URL;
  // ReplaySubject is an Observable
  public getCurrentUser = new ReplaySubject<User>(1);
  public loggedIn = new BehaviorSubject<boolean>(session.authenticated);
  public logOut: Observable<any>;
  public authenticate: (credentials: Credentials) => Observable<User>;
  public registerUser: (data: UserFormDataModel) => Observable<void>;
  public isLoggedIn = (): boolean => session.authenticated;
  private getAuthenticationHeaders: (credentials: Credentials) => HttpHeaders = (credentials: Credentials) => new HttpHeaders({
    authorization: 'basic ' + btoa(credentials.email + ':' + credentials.password)
  })


  constructor(private http: HttpClient) {

    this.authenticate = (credentials: Credentials) => this.http.post(
      this.BASE_URL + '/api/users/authenticate', '',
      { headers: this.getAuthenticationHeaders(credentials) })
      .pipe(
        tap((user: User) => {
          session.authenticate();
          this.getCurrentUser.next(user);
          this.loggedIn.next(true);
          console.log(`User '${user.userName}' succesfully logged in`);
        }),
      );


    this.logOut = this.http.get(this.BASE_URL + '/api/users/logout')
      .pipe(
        tap(() => {
          session.inauthenticate();
          this.loggedIn.next(false);
          console.log('User succesfully logged out');
        })
      );


    this.registerUser = (data: UserFormDataModel): Observable<void> =>
      this.http.post<void>(environment.BASE_URL + '/api/users/register', data);

    if (session.authenticated) {
      this.http.get<User>(this.BASE_URL + '/api/users/me')
        .subscribe(
          user => {
            this.getCurrentUser.next(user);
            this.loggedIn.next(true);
            console.log(`Succesfully fetched details of User '${user.userName}'`);
          },
          err => {
            session.inauthenticate();
            this.loggedIn.next(false);
            console.error(err);
          }
        );
    }

  }








}
