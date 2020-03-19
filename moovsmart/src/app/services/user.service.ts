import { Injectable } from '@angular/core';
import { UserFormDataModel } from '../models/userFormData.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/error/User';
import { AuthenticationService } from './authentication.service';
import { flatMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  BASE_URL = 'http://localhost:8080/api/users';
  private userId: number | null;
  public getCurrentUser: Observable<User>;

  constructor(
    private http: HttpClient,
    public authService: AuthenticationService) {
    this.userId = this.authService.getUserId();

    this.authService.loggedIn.subscribe((idFromLoginEvent: number) =>
      this.userId = idFromLoginEvent);

    this.authService.loggedOut.subscribe(logout => this.userId = null);

    this.getCurrentUser = this.userId
      ? this.fetchUserById(this.userId)
      : this.authService.loggedIn
        .pipe(
          flatMap(
            (idFromLoginEvent: number) =>
              this.fetchUserById(idFromLoginEvent)
          )
        );
  }

  getId(): number { return this.userId; }

  fetchUserById(id: number): Observable<User> {
    return this.http.get<User>(this.BASE_URL + `/${id}`);
  }

  registerUser(data: UserFormDataModel): Observable<void> {
    return this.http.post<void>(this.BASE_URL + '/register', data);
  }
  isLoggedIn = (): boolean => this.userId ? true : false;
}
