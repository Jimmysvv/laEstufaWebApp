import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

interface userPost {
  id: number;
  author_id: string;
  contents: string;
  image: string;
  date: string;
  login: string;
  avatar: string;
  length: any;
}

@Injectable()
export class UserPostBlockService {

  constructor(private _http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      // 'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('Token'),
      'Session': localStorage.getItem('Session'),
      'UserId': localStorage.getItem('UserId')
    })
  };

  getAllUserPosts() {
    return this._http.get<userPost>('/give/all');
  }

  getAllCurrentUserPosts(login: string) {
    let url = '/give/all/' + login;
    return this._http.get<userPost>(url);
  }

  getCurrentUserPosts(postId: string) {
    let url = '/give/current/' + postId;
    return this._http.get<userPost>(url);
  }

  getAllFollowingPosts(userId: string) {
    let url = '/get/followingPosts/' + userId;
    return this._http.get<userPost>(url, this.httpOptions);
  }
}
