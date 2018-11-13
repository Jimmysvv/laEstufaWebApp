import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface userPost {
  id: number;
  authorId: string;
  contents: string;
  image: string;
  date: string;
  login: string;
  avatar: string;
}

@Injectable()
export class UserPostBlockService {

  constructor(private _http: HttpClient) { }

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
}
