import {Component, OnInit} from '@angular/core';
import {UserPostBlockService} from './user-post-block.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {FollowingService} from '../following/following.service';

@Component({
  selector: 'app-user-post-block',
  templateUrl: './user-post-block.component.html',
  styleUrls: ['./user-post-block.component.css'],
  providers: [UserPostBlockService]
})
export class UserPostBlockComponent implements OnInit {

  userlogin: string = '';
  userPostsData: any = null;
  showSpinner: boolean;
  showError: boolean;
  loggedIn: boolean;
  postId: string;
  usersIdList: any = [];
  errorMessage = 'We\'re currently out of content for you.';

  constructor(private _userPostService: UserPostBlockService,
              private _followingService: FollowingService,
              private _route: ActivatedRoute,
              private _router: Router,
              private _auth: AuthService) {
    _route.params.subscribe(val => {
      this.showSpinner = true;
      const login = this._route.snapshot.paramMap.get('login');
      this.userlogin = login !== null ? login : '';
      const postId = this._route.snapshot.paramMap.get('postId');
      this.postId = postId !== null ? postId : '';
      const userId = localStorage.getItem('UserId');
      this.loggedIn = !!(userId || this._auth.isLoggedIn);
      if (this.userlogin) {
        this._userPostService.getAllCurrentUserPosts(login).subscribe(data => {
          this.success(data);
        }, () => {
          this.error();
          });
      } else if (this.postId) {
        this._userPostService.getCurrentUserPosts(postId).subscribe(data => {
          this.success(data);
        }, () => {
          this.error();
        });
      } else if (!this.loggedIn || this._router.url === '/discover') {
        this._userPostService.getAllUserPosts().subscribe(data => {
          this.success(data);
        }, () => {
          this.error();
        });
      } else if (this.loggedIn) {
        this._userPostService.getAllFollowingPosts(userId).subscribe(data => {
          this.showSpinner = false;
          if (data.length === 0 || !data[0].hasOwnProperty('login')) {
            this.errorMessage = 'There\'s no post. Follow someone with content.';
            this.showError = true;
          } else {
            this.success(data);
          }
        }, () => {
          this.error();
        });
      }
      this._followingService.getUserFollowing(localStorage.getItem('UserId')).subscribe(data => {
        const list = [];
        for (const self of data) {
          list.push(self.following);
        }
        localStorage.setItem('following', list.toString());
      });
    });
  }

  ngOnInit() { }

  goToUser(userLogin) {
    this._router.navigate(['/user', userLogin]);
  }

  atob(url) {
    return atob(url);
  }

  success(data: any) {
    this.showSpinner = false;
    this.userPostsData = data;
    data.forEach(a => {
      this.usersIdList.push(a.author_id);
    });
    this.usersIdList = this.usersIdList.filter((x, i, a) => a.indexOf(x) === i);
  }

  error() {
    this.showSpinner = false;
    this.showError = true;
  }

  transformTags(val) {
    const regex = /\B(\#[a-zA-Z_1-9]+\b)(?!;)/g;
    const matches = this.getMatches(val, regex, 1);
    // find da way to routerLink properly
    for (let match of matches) {
      const replace = '<a routerLink="/search/' + match.substring(1) + '" class="uk-padding-remove uk-text-small">' +
        match + '&nbsp;</a>';
      val = val.replace(match, replace);
    }
    return val;
  }

  getMatches(string, regex, index) {
    let matches = [];
    let match;
    while (match = regex.exec(string)) {
      matches.push(match[index]);
    }
    return matches;
  }
}
