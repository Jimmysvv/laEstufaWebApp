import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';

interface response {
  status: boolean;
}

interface loginToken {
  Token: string;
  Session: string;
  UserId: string;
  UserLogin: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  token: string = localStorage.getItem('Token') ? localStorage.getItem('Token') : null;
  session: string = localStorage.getItem('Session') ? localStorage.getItem('Session') : null;
  userId: string = localStorage.getItem('UserId') ? localStorage.getItem('UserId') : null;

  constructor(private _http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.token,
      'Session': this.session,
      'UserId': this.userId
    })
  };

  isLoggedIn(): Observable<response> {
    return this._http.get<response>('/loginStatus', this.httpOptions);
  }

  logout() {
    return this._http.get<response>('/get/logout', this.httpOptions);
  }

  doLoginUser(email, password) {
    return this._http.post<loginToken>('/login/token', {
      email,
      password
    });
  }
}
