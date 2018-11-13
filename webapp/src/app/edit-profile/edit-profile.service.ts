import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

interface response {
  status: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class EditProfileService {

  constructor(private _http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('Token'),
      'Session': localStorage.getItem('Session'),
      'UserId': localStorage.getItem('UserId')
    })
  };

  editUserProfile(formData) {
    return this._http.post<response>('/get/user/edit', formData, this.httpOptions);
  }
}
