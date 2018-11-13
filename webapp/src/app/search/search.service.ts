import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

interface result {
  id: number;
  avatar: string;
  login: string;
  image: string;
  contents: string;
}

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  httpOptions: any;

  constructor(private _http: HttpClient) { }

  getSearchResult(value, type) {
    const myUrl = '/get/searching/' + type;
    return this._http.get<result>(myUrl, this.setHeader(value));
  }

  setHeader(value: string) {
    return this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('Token'),
        'value': value
      })
    };
  }
}
