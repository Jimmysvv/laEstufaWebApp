import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

interface follow {
  follower: string;
  following: string;
  login: string;
  avatar: string;
}

@Injectable({
  providedIn: 'root'
})
export class FollowingService {

  constructor(private _http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      // 'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('Token'),
      'Session': localStorage.getItem('Session'),
      'UserId': localStorage.getItem('UserId')
    })
  };

  getUserFollowing(authorId: string) {
    let url = '/get/following/' + authorId;
    return this._http.get<follow>(url, this.httpOptions);
  }

  getUserFollowers(authorId: string) {
    let url = '/get/followers/' + authorId;
    return this._http.get<follow>(url, this.httpOptions);
  }

  getUserFollowCounter(userId: number) {
    let url = '/followCounter/' + userId;
    return this._http.get<follow>(url);
  }

  checkForFollowing(authorId: string, userFollowing: any) {
    return authorId === localStorage.getItem('UserId') ?
      true : Array.from(userFollowing.split(',')).find(val => val === authorId);
  }

  follow(authorId: string) {
    let url = '/get/follow/';
    const followModel = <follow>{};
    followModel.follower = localStorage.getItem('UserId');
    followModel.following = authorId;
    return this._http.post<boolean>(url, followModel, this.httpOptions);
  }
}
