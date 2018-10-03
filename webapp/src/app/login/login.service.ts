import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private _http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa('clientId:secret'),
    })
  };

  token: any = [];

  doLoginUser(email, password) {
    this._http.post('/login/token', {
      email,
      password
    }).subscribe(res => {
      this.token = res;
      console.log(this.token);
    });
  }
}
