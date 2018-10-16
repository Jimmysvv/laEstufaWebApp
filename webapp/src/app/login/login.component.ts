import { Component, OnInit } from '@angular/core';
import {LoginService} from './login.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../auth.service';

interface loginToken {
  Token: string;
  Session: string;
  UserId: string;
  UserLogin: string;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [ LoginService ]
})
export class LoginComponent implements OnInit {

  myErrorMessage: boolean;
  myErrorMessageVal = 'invalid credentials';

  constructor(private _loginService: LoginService,
              private _route: ActivatedRoute,
              private _router: Router,
              private _auth: AuthService) {
    this._route.params.subscribe(value => {
      this.myErrorMessage = false;
      this._loginService.isLoggedIn()
        .subscribe(res => {
          if (res.status) {
            this._router.navigate(['']);
          }
        });
    });
  }

  ngOnInit() { }

  doLoginUser(event) {
    event.preventDefault();
    const target = event.target;
    const email = target.querySelector('#email').value;
    const password = target.querySelector('#password').value;
    this._loginService.doLoginUser(email, password).subscribe(res => {
      if (res != null) {
        this.setUserSessionStorage(res);
        this.setUserLocalStorage(res);
        this._auth.setUserLoggedIn(true);
        location.reload();
        this._router.navigate(['']);
      } else {
        this.myErrorMessage = true;
      }
    });
  }

  setUserSessionStorage(value: loginToken) {
    sessionStorage.setItem('Token', value.Token);
    sessionStorage.setItem('Session', value.Session);
    sessionStorage.setItem('UserId', value.UserId);
    sessionStorage.setItem('UserLogin', value.UserLogin);
  }

  setUserLocalStorage(value: loginToken) {
    localStorage.setItem('Token', value.Token);
    localStorage.setItem('Session', value.Session);
    localStorage.setItem('UserId', value.UserId);
    localStorage.setItem('UserLogin', value.UserLogin);
  }
}
