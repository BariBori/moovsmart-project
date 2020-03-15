import {Injectable} from '@angular/core';
import {UserFormDataModel} from '../models/userFormData.model';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  BASE_URL = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {
  }

  createUser(data: UserFormDataModel): Observable<void> {
    return this.http.post<void>(this.BASE_URL + '/register', data);
  }

  getCredentials = (): Observable<string> => this.http.get<string>(this.BASE_URL + '/me');

}
