import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-more',
  templateUrl: './more.component.html',
  styleUrls: ['./more.component.css']
})
export class MoreComponent implements OnInit {

  @Input() loggedIn: boolean;
  @Input() authorId: string;
  isLoggedIn: boolean;
  isPostAuthor: boolean;

  constructor(private _route: ActivatedRoute) {
    this._route.params.subscribe(val => {
      this.isLoggedIn = this.loggedIn;
      this.isPostAuthor = !!(this.authorId === localStorage.getItem('UserLogin'));
    });
  }

  ngOnInit() {
    this.isLoggedIn = this.loggedIn;
    this.isPostAuthor = !!(this.authorId === localStorage.getItem('UserLogin'));
  }

}
