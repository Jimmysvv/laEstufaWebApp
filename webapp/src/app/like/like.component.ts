import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.css']
})
export class LikeComponent implements OnInit {

  @Input() loggedIn: boolean;
  isLoggedIn: boolean;

  constructor(private _route: ActivatedRoute) {
    this._route.params.subscribe(val => {
      this.isLoggedIn = this.loggedIn;
    });
  }

  ngOnInit() {
    this.isLoggedIn = this.loggedIn;
  }

}
