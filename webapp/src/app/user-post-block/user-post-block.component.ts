import { Component, OnInit } from '@angular/core';
import { UserPostBlockService } from './user-post-block.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-post-block',
  templateUrl: './user-post-block.component.html',
  styleUrls: ['./user-post-block.component.css'],
  providers: [UserPostBlockService]
})
export class UserPostBlockComponent implements OnInit {

  userpostsdata: any = [];

  constructor(private _userPostService: UserPostBlockService,
              private _router: Router) {  }

  ngOnInit() {
    this._userPostService.getAllUserPosts().subscribe(data => {
      this.userpostsdata = data;
    });
  }

  goToUser(userLogin) {
    this._router.navigate(['/user', userLogin]);
  }
}
