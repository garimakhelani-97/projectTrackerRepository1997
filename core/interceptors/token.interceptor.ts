import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse
} from '@angular/common/http';
import { RefreshTokenService } from '../services/refresh-token.service';
// import { AuthService } from './auth/auth.service';
import {environment} from '../../../environments/environment';
import { Observable, of } from 'rxjs';

const TOKEN_HEADER_KEY = 'Authorization';
@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(public refreshTokenService: RefreshTokenService) {
    //console.log("inter")
  }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    //console.log("interceptor", request)
    const enableSessionTimeout = environment.enableSessionTimeout;
    // console.log(request.url)
    // if(request.method === "POST" && request.url === "auth") {
    //       console.log(request.url);
    //       return of(new HttpResponse({ status: 200, body: {token: 'eyghjuynh'} }));
    // }
    if (this.refreshTokenService.getToken() != null) {
      request = request.clone(
        {
          headers: request.headers.set(
            TOKEN_HEADER_KEY, `Bearer ${this.refreshTokenService.getToken()}`
          )
        }
      );
    }
    if (enableSessionTimeout) {
      // this.refreshTokenService.onNetworkActivity(request.url);
    }
    return next.handle(request);
  }
}