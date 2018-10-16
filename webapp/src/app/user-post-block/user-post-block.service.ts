import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class UserPostBlockService {

  constructor(private _http: HttpClient) { }

  getAllUserPosts() {
    return this._http.get('/give/all');
  }

  getAllCurrentUserPosts(login: string) {
    let url = '/give/all/' + login;
    return this._http.get(url);
  }
}
