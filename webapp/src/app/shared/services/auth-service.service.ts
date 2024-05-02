import { UserLoginService } from 'src/app/shared/services/user-login.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterModel } from '../model/register.model';
import { Observable } from 'rxjs';
import { Message } from '../model/message.model';
import { LoginModel } from '../model/login.model';
import { map } from 'rxjs/operators';
import { JWTResponseModel } from '../model/JWTResponse.model';

@Injectable({
  providedIn: 'root',
})
export class AuthServiceService {
  constructor(private httpClient: HttpClient , private userLoginService: UserLoginService) {}
  public register(register: RegisterModel): Observable<Message> {
    return this.httpClient.post<Message>(
      'api/auth/users/save',
      register
    );
  }

  public login(login: LoginModel): Observable<JWTResponseModel> {
    return this.httpClient.post<JWTResponseModel>(
      'api/auth/login',
      login
    ).pipe(map((data) => {
      if(data.success){
        this.userLoginService.login(data)
      }
      return data
    }))
  }
}
