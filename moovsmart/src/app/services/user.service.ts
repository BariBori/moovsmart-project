import { Injectable } from '@angular/core';
import { UserFormDataModel } from '../models/userFormData.model';
import { Observable, ReplaySubject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/error/User';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Credentials } from '../models/Credentials';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  public authenticate: (credentials: Credentials) => Observable<User>;
  // ReplaySubject is an Observable
  public getCurrentUser: ReplaySubject<User>;
  public logOut: Observable<void>;

  private BASE_URL = environment.BASE_URL;
  private getAuthenticationHeaders: (credentials: Credentials) => HttpHeaders;
  public isLoggedIn: () => boolean;
  registerUser: (data: UserFormDataModel) => Observable<void>;

  constructor(private http: HttpClient) {

    this.getCurrentUser = new ReplaySubject<User>(1);

    this.authenticate = (credentials: Credentials) => this.http.post(
      this.BASE_URL + '/api/users/authenticate', '',
      { headers: this.getAuthenticationHeaders(credentials) })
      .pipe(
        tap((user: User) => {
          localStorage.setItem('authenticated', 'true');
          this.getCurrentUser.next(user);
          console.log(`User '${user.userName}' succesfully logged in`);
        }),
      );

    this.getAuthenticationHeaders = (credentials: Credentials) => new HttpHeaders({
      authorization: 'basic ' + btoa(credentials.email + ':' + credentials.password)
    });

    this.logOut = this.http.get<void>(this.BASE_URL + '/api/users/logout')
      .pipe(
        tap(() => {
          localStorage.setItem('authenticated', '');
          this.getCurrentUser.error(null);
          console.log('User succesfully logged out');
        })
      );

    this.isLoggedIn = () => Boolean(sessionStorage.getItem('authenticated'));

    this.registerUser = (data: UserFormDataModel): Observable<void> =>
      this.http.post<void>(environment.BASE_URL + '/api/users/register', data);

    console.log(this.isLoggedIn())
    if (this.isLoggedIn()) {
      this.http.get<User>(this.BASE_URL + 'api/users/me')
        .pipe(
          tap(
            user => console.log(`Succesfully fetched details of User '${user.userName}'`),
            err => console.error(err)
          )
        )
        .subscribe(user => this.getCurrentUser.next(user));
    }

  }








}
