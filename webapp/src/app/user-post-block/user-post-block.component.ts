import { Component, OnInit } from '@angular/core';
import { UserPostBlockService } from './user-post-block.service';
import {ActivatedRoute, Router} from '@angular/router';

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
  postId: string;

  constructor(private _userPostService: UserPostBlockService,
              private _route: ActivatedRoute,
              private _router: Router) {
    _route.params.subscribe(val => {
      this.showSpinner = true;
      const login = this._route.snapshot.paramMap.get('login');
      this.userlogin = login !== null ? login : '';
      const postId = this._route.snapshot.paramMap.get('postId');
      this.postId = postId !== null ? postId : '';
      if (this.userlogin) {
        this._userPostService.getAllCurrentUserPosts(login).subscribe(data => {
          this.showSpinner = false;
          this.userPostsData = data;
        }, () => {
          this.showSpinner = false;
          this.showError = true;
          });
      } else if (this.postId) {
        this._userPostService.getCurrentUserPosts(postId).subscribe(data => {
          this.showSpinner = false;
          this.userPostsData = data;
        }, () => {
          this.showSpinner = false;
          this.showError = true;
        });
      } else {
        this._userPostService.getAllUserPosts().subscribe(data => {
          this.showSpinner = false;
          this.userPostsData = data;
        }, () => {
          this.showSpinner = false;
          this.showError = true;
        });
      }
    });
  }

  ngOnInit() { }

  goToUser(userLogin) {
    this._router.navigate(['/user', userLogin]);
  }

  atob(url) {
    return atob(url);
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
