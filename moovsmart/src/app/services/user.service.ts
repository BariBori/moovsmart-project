import { Injectable } from '@angular/core';
import { UserFormDataModel } from '../models/userFormData.model';
import { Observable, ReplaySubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/error/User';
import { AuthenticationService } from './authentication.service';
import { tap, first } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  BASE_URL = 'http://localhost:8080/api/users';

  public getCurrentUser: Observable<User>;
  private userUpdater: ReplaySubject<User>;

  constructor(
    private http: HttpClient,
    public authService: AuthenticationService) {


    this.userUpdater = new ReplaySubject<User>();
    this.userUpdater.error(null);
    authService.loggedOut.subscribe(() => this.userUpdater.error(null));
    authService.loggedIn.subscribe((user: User) => this.userUpdater.next(user));
    this.getCurrentUser = this.userUpdater.pipe(
      tap(maybeUser => console.log(`Succesfully fetched details of User '${maybeUser?.userName}'`)),
      first(),
    );
  }

  registerUser(data: UserFormDataModel): Observable<void> {
    return this.http.post<void>(this.BASE_URL + '/register', data);
  }
}
