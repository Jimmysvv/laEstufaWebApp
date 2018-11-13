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
  userProfileDetails: boolean;
  showSpinner: boolean;
  showError: boolean;

  constructor(private _route: ActivatedRoute,
              private _router: Router,
              private _userProfileService: UserProfileService) {
    _route.params.subscribe(val => {
      this.userlogin = this._route.snapshot.paramMap.get('login');
      this.showSpinner = true;
      if (this.userlogin) {
        this._userProfileService.getUserData(this.userlogin).subscribe(data => {
          if (data.login) {
            data.avatar = atob(data.avatar);
            this.userData = data;
            this.userProfileDetails = this.checkForUserProfileDetails(this.userData);
            this.showSpinner = false;
          } else {
            this.showSpinner = false;
            this.showError = true;
          }
        }, () => {
          this.showSpinner = false;
          this.showError = true;
        });
      }
    });
  }

  ngOnInit() { }

  private checkForUserProfileDetails(data: any) {
    return (data.name && data.last_name && data.description) !== null;
  }
}
