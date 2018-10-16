import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private _http: HttpClient) { }

  headers = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Register': 'true'
    })
  };

  doRegisterNewUser(login, email, password, rodo) {
    this._http.post('/register/new/user', {
      login,
      email,
      password,
      rodo
    }, this.headers).subscribe(res => {
      console.log('Successfully registrated user: ', email);
    });
  }
}
