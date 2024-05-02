import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class BaseUrlInterceptor implements HttpInterceptor {
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (!environment.production) {

      const baseUrl = environment.baseUrl; // Get the base URL from environment
      const apiReq = request.clone({ url: `${baseUrl}/${request.url}` }); // Append base URL to request
      return next.handle(apiReq);
    }

    return next.handle(request);
  }
}
