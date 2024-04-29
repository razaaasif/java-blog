import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterModel } from '../../model/register.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthServiceService {
  constructor(private httpClient: HttpClient) {}

  public register(register: RegisterModel):Observable<RegisterModel> {
    return this.httpClient.post<RegisterModel>(
      'http://localhost:8080/api/users/save',
      register
    );
  }
}
