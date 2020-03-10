import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Credentials } from '../models/Credentials';
import { Observable, Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private BASE_URL = 'http://localhost:8080:users';

  public credentials: Credentials;

  constructor(private http: HttpClient) { }

  authenticate = (credentials: Credentials): Observable<void> => this.http.get<void>(
    this.BASE_URL + '/authenticate',
    { headers: this.getAuthenticationHeaders(credentials) }
  )



  isLoggedIn = (): boolean => this.credentials ? true : false;

  logOut = (): void => this.credentials = null;

  public getAuthenticationHeaders = (credentials: Credentials) => new HttpHeaders({
    authorization: 'basic ' + btoa(credentials.email + ':' + credentials.password)
  })
}
