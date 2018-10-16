import { Component, OnInit } from '@angular/core';
import {LoginService} from '../login/login.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn: boolean;
  userLogin: string;

  constructor(private _route: ActivatedRoute,
              private _login: LoginService) {
    _route.params.subscribe(val => {
      this._login.isLoggedIn()
        .subscribe(res => {
          if (res.status) {
            this.isLoggedIn = res.status;
            localStorage.getItem('UserLogin') ? this.userLogin = localStorage.getItem('UserLogin') : this.userLogin = '';
          }
      });
    });
  }

  ngOnInit() { }

  doLogout() {
    this._login.logout().subscribe(res => {
      if (res.status) {
        this.isLoggedIn = false;
        sessionStorage.clear();
        localStorage.clear();
        location.reload();
      }
    });
  }
}
