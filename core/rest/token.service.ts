import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private httpClient: HttpClient) { }

  refreshToken(): Observable<any> {
    const url = `/refresh`;
    return this.httpClient.get(url);
  }
}
