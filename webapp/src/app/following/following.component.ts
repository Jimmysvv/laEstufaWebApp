import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {FollowingService} from './following.service';
import {ActivatedRoute, Router} from '@angular/router';
import * as UIkit from 'uikit';

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {

  @Input() authorsIds: any = [];
  @Input() authorId: string;
  @Input() authorLogin: string;
  @Input() loggedIn: boolean;
  @Input() userProfile: boolean;
  @Input() postCounter: number;
  @Input() userlogin: string;
  @ViewChild('followInfo') followInfo;
  value = 'FOLLOW';
  isLoggedIn: boolean;
  userProfilePage: boolean;
  @Input() userFollowCounter: any;

  constructor(private _followingService: FollowingService,
              private _route: ActivatedRoute,
              private _router: Router) {
    this._route.params.subscribe(val => {
      this.isLoggedIn = this.loggedIn;
      const link = this._router.url;
      const regex = /((?:^|\/)(user)(?=\/|$)){1}/g;
      if (regex.test(link)) {
        this.userProfilePage = true;
      }
    });
  }

  ngOnInit() {
    this.isLoggedIn = this.loggedIn;
  }

  checkForFollowing(authorId: string) {
    return this._followingService.checkForFollowing(authorId, localStorage.getItem('following'));
  }

  follow(event) {
    event.preventDefault();
    this._followingService.follow(this.authorId).subscribe(res => {
      if (res) {
        UIkit.notification({
          message: '<span uk-icon=\'icon: user\'></span>&nbsp;' + this.authorLogin + ' is now followed',
          status: 'primary',
          timeout: 1300
        });
        this.followInfo.hidden(res);
        this.value = '';
      }
    });
  }
}
