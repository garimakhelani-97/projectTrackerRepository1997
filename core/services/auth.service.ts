import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RefreshTokenService } from './refresh-token.service';
import { environment } from '../../../environments/environment';
import { Router } from '@angular/router';
import { StartupService } from './startup.service';
import { ContextService } from './context.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn = false;
  constructor(private httpClient: HttpClient,
              private refreshTokenService: RefreshTokenService,
              private router: Router,
              private startupService: StartupService,
              private contextService: ContextService) { }

  attempLogin(username: string, password: string) {
    const credentials = { email: 'xyz1234@gmail.com', password: '12345699' };
    const url = environment.apiBaseUrl + "users-ws/login";
    this.httpClient.post(url, credentials, {
      headers: new HttpHeaders()
    }).subscribe((data: any) => {
      this.contextService.userId = data.userId;
      this.authSuccess(data.token);
      this.isLoggedIn = true;
      this.startupService.load();
      this.getData();
    });
  }

  authSuccess(token: string) {
    this.refreshTokenService.saveToken(token);
  }

  getData() {
    const url = environment.apiBaseUrl + "users-ws/users/status/check"
    this.httpClient.get(url).subscribe(data => {
      this.router.navigate(['home']);
    });
  }
}
