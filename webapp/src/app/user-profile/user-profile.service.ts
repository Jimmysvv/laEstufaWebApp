import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

interface user {
  login: string;
  email: string;
  name: string;
  last_name: string;
  birthDay: any;
  is_private: boolean;
  enable_mat: boolean;
  description: string;
  avatar: string;
  id: number;
}

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  url: string;

  token: string = localStorage.getItem('Token') ? localStorage.getItem('Token') : null;
  session: string = localStorage.getItem('Session') ? localStorage.getItem('Session') : null;
  userId: string = localStorage.getItem('UserId') ? localStorage.getItem('UserId') : null;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.token,
      'Session': this.session,
      'UserId': this.userId
    })
  };

  constructor(private _http: HttpClient) { }

  getUserData(login: string) {
    this.url = '/give/user/' + login;
    return this._http.get<user>(this.url);
  }

  getPersonalUserData(login: string) {
    this.url = '/get/user/' + login;
    return this._http.get<user>(this.url, this.httpOptions);
  }
}
