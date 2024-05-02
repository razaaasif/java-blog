import { LocalStorageService } from 'ngx-webstorage';
import { JWTResponseModel } from './../../model/JWTResponse.model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserLoginService {
  private readonly TOKEN = 'token';
  private readonly USERNAME = 'username';
  constructor(private $localStorageService: LocalStorageService) {}
  login(token: JWTResponseModel) {
    this.$localStorageService.store(this.TOKEN, token.accessToken);
    this.$localStorageService.store(this.USERNAME, token.username);
  }
  logout() {
    this.$localStorageService.clear(this.TOKEN);
    this.$localStorageService.clear(this.USERNAME);
  }

  public isAuthenitcated(): boolean {
     return this.$localStorageService.retrieve(this.TOKEN)!= null && this.$localStorageService.retrieve(this.USERNAME)!= null;
  }

  getToken(): string {
    return this.$localStorageService.retrieve(this.TOKEN);
  }

  getCurrentUser(): string {
    return this.$localStorageService.retrieve(this.USERNAME);
  }
}
