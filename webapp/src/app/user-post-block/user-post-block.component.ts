import { Component, OnInit } from '@angular/core';
import { UserPostBlockService } from './user-post-block.service';
import {ActivatedRoute, Router} from '@angular/router';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-user-post-block',
  templateUrl: './user-post-block.component.html',
  styleUrls: ['./user-post-block.component.css'],
  providers: [UserPostBlockService]
})
export class UserPostBlockComponent implements OnInit {

  userlogin: string;
  userPostsData: any = [];
  path: string = 'C:\/Projekty\/';

  constructor(private _userPostService: UserPostBlockService,
              private _route: ActivatedRoute,
              private _router: Router,
              private _sanitizer: DomSanitizer) {
    _route.params.subscribe(val => {
      const login = this._route.snapshot.paramMap.get('login');
      login !== null ? this.userlogin = login : this.userlogin = '';
      if (this.userlogin.length > 1) {
        this._userPostService.getAllCurrentUserPosts(login).subscribe(data => {
          this.userPostsData = data;
        });
      } else {
        this._userPostService.getAllUserPosts().subscribe(data => {
          this.userPostsData = data;
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
}
