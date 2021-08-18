import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PrivilegeService {

  constructor() { }

  hasScreenPrivilege(moduleUrl, screenUrl): any {
    return true;
  }
}
