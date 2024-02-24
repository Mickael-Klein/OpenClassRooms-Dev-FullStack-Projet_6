import { HttpHandlerFn, HttpHeaders, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { SessionService } from '../core/service/session/session.service';

export function jwtInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn) {
  if (!req.url.includes('/auth')) {
    const jwt = localStorage.getItem('jwt');
    const headers = new HttpHeaders().append('Authorization', `Bearer ${jwt}`);
    req = req.clone({ headers });
  }

  return next(req);
}