import { Injectable } from '@angular/core';
import { UserFormDataModel } from '../models/userFormData.model';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/error/User';
import { AuthenticationService } from './authentication.service';
import { flatMap, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  BASE_URL = 'http://localhost:8080/api/users';

  public getCurrentUser: Observable<User | null>;
  private userUpdater: BehaviorSubject<User | null>;

  constructor(
    private http: HttpClient,
    public authService: AuthenticationService) {


    this.userUpdater = new BehaviorSubject<User | null>(null);
    authService.loggedOut.subscribe(() => this.userUpdater.next(null));
    authService.loggedIn.subscribe(user => this.userUpdater.next(user));
    this.getCurrentUser = this.userUpdater
      .pipe(tap(maybeUser => console.log(`Succesfully fetched details of User '${maybeUser?.userName}'`)));
  }

  registerUser(data: UserFormDataModel): Observable<void> {
    return this.http.post<void>(this.BASE_URL + '/register', data);
  }
}
