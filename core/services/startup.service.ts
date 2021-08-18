import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { menuIcons } from '../../mappings/menu-icon-map';
import { MenuService } from './menu.service';
import { environment } from '@env/environment';

@Injectable()
export class StartupService {
  constructor(private menuService: MenuService, private http: HttpClient) {}

  data: any = {};
  load() {
    const langPath = `assets/data/menu.json`;
    const url = environment.apiBaseUrl + 'users-ws/users/roles';
    this.http
        .get(url, {
            params: new HttpParams()
              .set('userId', `${window.sessionStorage.getItem('userId')}`)
        }).subscribe(
          (res: any) => {
            const roles = res.roles;
            res.menu.map(info => {
              console.log("info", info)
              info["icon"] = menuIcons[info.menuKey];
            });
            res.menu.sort((m1, m2) => m1.order - m2.order);
            this.menuService.set(res.menu);
          },
          () => {},
          () => {
          }
        );
  }

  use(lang: string): Promise<any> {
    return new Promise((resolve, reject) => {
      const langPath = `assets/language/${lang || 'en'}.json`;
      this.http
        .get(langPath)
        .pipe(
          catchError(res => {
            resolve();
            return res;
          })
        )
        .subscribe(
          (res: any) => {
              this.data = Object.assign({}, res || {});
              resolve(this.data.json);
          },
          () => {},
          () => {
            resolve();
          }
        );
    });
  }
}
