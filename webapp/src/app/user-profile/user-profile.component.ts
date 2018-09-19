import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {UserProfileService} from './user-profile.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  userlogin: string;
  userData: any = [];

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _userProfileService: UserProfileService
  ) { }

  ngOnInit() {
    let login = this._route.snapshot.paramMap.get('login');
    this.userlogin = login;
    this._userProfileService.getUserData(login).subscribe(data => {
      this.userData = data;
    });
  }

}
