import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

interface response {
  message: string;
  success: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class CreateNewPostService {

  constructor(private _http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      // 'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('Token'),
      'Session': localStorage.getItem('Session'),
      'UserId': localStorage.getItem('UserId')
    })
  };

  createNewPost(contents, authorId, date, image) {
    return this._http.post<response>('/get/post/create', {
      contents,
      authorId,
      date,
      image
    }, this.httpOptions);
  }

  uploadFile(file) {
    return this._http.post<response>('/get/post/upload', file, this.httpOptions);
  }
}
