import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ContextService } from '@core/services/context.service';
import { Observable } from 'rxjs';
import { ProjectList } from './types/project-list-type';

@Injectable({
  providedIn: 'root'
})
export class GporService {

  constructor(private httpClient: HttpClient,
              private contextService: ContextService) { }

  getProjectList(): Observable<ProjectList[]> {
    //const url = `${this.contextService.apiBaseUrl}users-ws/users/status/check`;
    const url = `assets/data/project-list.json`;
    return this.httpClient.get<ProjectList[]>(url, {
      params: new HttpParams()
        .set('userId', `${this.contextService.userId}`)
    });
  }
}
