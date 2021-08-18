import { Injectable } from '@angular/core';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class ContextService {
  apiBaseUrl = environment.apiBaseUrl;
  constructor() { }

  set userId(id: string) {
    window.sessionStorage.setItem('userId', id);
  }

  get userId(): string {
    return window.sessionStorage.getItem('userId');
  }
}
