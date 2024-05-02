import { UserLoginService } from 'src/app/shared/services/user-login.service';
import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class BlogInterceptor implements HttpInterceptor {
  constructor(private userLoginService: UserLoginService, private router: Router) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    const token = this.userLoginService.getToken();
    console.log(token);
    if (token) {
      const clonedRequest = request.clone({
        headers: request.headers.set('Authorization', 'Bearer ' + token),
      });
      return next.handle(clonedRequest);
    } else if(!request.url.includes('api/posts')){
      console.log('url' + request.url);
      this.router.navigateByUrl('/login');
    }
    return next.handle(request);
  }
}
