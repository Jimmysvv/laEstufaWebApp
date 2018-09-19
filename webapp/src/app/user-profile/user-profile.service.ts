import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  constructor(private _http: HttpClient) { }

  getUserData(login: string) {
    let url = '/get/user/' + login;
    return this._http.get(url);
  }
}
