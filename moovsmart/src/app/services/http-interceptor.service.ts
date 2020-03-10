import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class HttpInterceptorService implements HttpInterceptor {

  constructor(private service: AuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(
      this.service.isLoggedIn()
        ? request.clone({
          headers: this.service.getAuthenticationHeaders(this.service.credentials)
        })
        : request
    );
  }
}
