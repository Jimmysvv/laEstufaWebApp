import { Component, OnInit } from '@angular/core';
import {RegisterService} from './register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private _registerService: RegisterService) { }

  ngOnInit() {
  }

  doRegisterNewUser(event) {
    event.preventDefault();
    const target = event.target;
    const login = target.querySelector('#login').value;
    const email = target.querySelector('#email').value;
    const password = target.querySelector('#password').value;
    const rodo = target.querySelector('#rodo').value;
    this._registerService.doRegisterNewUser(login, email, password, rodo);
  }

}
