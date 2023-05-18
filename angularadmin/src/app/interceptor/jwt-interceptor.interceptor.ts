import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Injectable()
export class JwtInterceptorInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log('Interceptorerere executing...');
    // Exclude the login endpoint from interception
    if (request.url.endsWith('/api/login')) {
      return next.handle(request);
    }

    // Clone the request and set the headers from the AuthService
    const headers = this.authService.httpOptions.headers;
    request = request.clone({ headers });
    console.log(request);
    return next.handle(request);
  }
}
