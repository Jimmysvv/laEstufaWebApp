import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthService} from './auth.service';
import {map} from 'rxjs/operators';
import {LoginService} from './login/login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private _auth: AuthService,
              private _router: Router,
              private _login: LoginService) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this._auth.isLoggedIn) {
      return true;
    }
    return this._login.isLoggedIn().pipe(map(res => {
      if (res) {
        this._auth.setUserLoggedIn(true);
        return true;
      } else {
        this._router.navigate(['singin']);
        return false;
      }
    }));
  }
}
