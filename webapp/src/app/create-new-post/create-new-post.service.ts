import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CreateNewPostService {

  constructor(private _http: HttpClient) { }

  createNewPost(contents, authorId, date, hasImage) {
    this._http.post('/get/create', {
      contents,
      authorId,
      date,
      hasImage
    }).subscribe(data => {
      console.log('response: ', data);
    });
  }
}
