import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { PrivilegeService } from '@core/services/privilege.service';

@Injectable({
  providedIn: 'root'
})
export class ScreenGuard implements CanActivate {
  constructor(private privilegeService: PrivilegeService) { }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const moduleUrl = state.url.substring(0, state.url.indexOf('/', 1));
    const screenUrl = next.url[0].path;
    return this.privilegeService.hasScreenPrivilege(moduleUrl, screenUrl);
  }
}
