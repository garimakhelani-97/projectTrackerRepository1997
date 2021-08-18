import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { TokenService } from '../rest/token.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RefreshTokenService {

  readonly TOKEN_KEY = 'AuthToken';
  tokenTime: Date;
  passedTime = 0;
  interval = environment.TokenRefreshIntervalMinutes;

  constructor(private tokenService: TokenService, private router: Router) { }

  onNetworkActivity(url: string) {
    if (this.tokenTime) {
      console.log('Inside onNetworkActivity calling');
      if (this.doRefreshCall()) {
          this.tokenService.refreshToken().subscribe(res => {
            this.saveToken(res.token);
          });
        } else {
          this.onTimeout();
        }
        // this.sessionRefreshRequired = true
        // const currentTime = new Date().getTime()
        // this.subscribeShowPopup(currentTime)

      // else { this.onTimeout() }
    }

  }

  doRefreshCall(): boolean {
    let isRefreshCall = false;
    this.passedTime = new Date().getTime() - this.tokenTime.getTime();
    const diffInterval = this.getInterval() - this.passedTime;
    isRefreshCall = this.passedTime < this.getInterval() && diffInterval === (2 * 60 * 1000) ? true : false;
    return isRefreshCall;
  }

  getInterval() {
    return (this.interval * 60 * 1000);
  }

  getToken(): string {
    return sessionStorage.getItem(this.TOKEN_KEY);
  }

  saveToken(token: string): void {
    this.tokenTime = new Date();
    window.sessionStorage.removeItem(this.TOKEN_KEY);
    window.sessionStorage.setItem(this.TOKEN_KEY, token);
  }

  onTimeout() {
    // this.closeAllModals()
    // this.alertService.show(new Alert('Message!', 'info', 'Your session has been expired. Please relogin to continue. ', 0))
    // this.subscription.unsubscribe()
    this.router.navigate(['/login']);
  }
}
