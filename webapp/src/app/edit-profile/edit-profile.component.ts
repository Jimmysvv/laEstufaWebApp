import {Component, OnInit} from '@angular/core';
import {EditProfileService} from './edit-profile.service';
import {ActivatedRoute} from '@angular/router';
import * as UIkit from 'uikit';
import {ImageCroppedEvent} from 'ngx-image-cropper/src/image-cropper.component';
import {UserProfileService} from '../user-profile/user-profile.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  login: string;
  email: string;
  name: string;
  lastName: string;
  birthDay: any;
  isPrivate: boolean;
  enableMat: boolean;
  description: string;
  avatar: string;
  orgAv: string;
  result: any = {};

  constructor(private _edit: EditProfileService,
              private _route: ActivatedRoute,
              private _user: UserProfileService) {
    this._route.params.subscribe(val => {
      const login = this._route.snapshot.paramMap.get('login');
      this.login = login;
      this._user.getPersonalUserData(login).subscribe(user => {
        if (user) {
          this.email = user.email;
          this.name = user.name;
          this.lastName = user.last_name;
          this.birthDay = user.birthDay;
          this.isPrivate = user.is_private;
          this.enableMat = user.enable_mat;
          this.description = user.description;
          this.avatar = atob(user.avatar);
          this.orgAv = atob(user.avatar);
        }
      });
    });
  }

  imageChangedEvent: any = '';
  croppedImage: any = '';

  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
  }
  imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = event.base64;
  }
  imageLoaded() {
    // show cropper
  }
  loadImageFailed() {
    UIkit.notification({
      message: '<span uk-icon=\'icon: warning\'></span> Image upload failed',
      status: 'danger',
      timeout: 500
    });
  }

  submitNewAvatar(event) {
    event.preventDefault();
    this.avatar = this.croppedImage;
  }

  loger(event) {
    console.log('is on: ', event.target.checked);
  }

  ngOnInit() {
  }

  editUserProfile(event) {
    event.preventDefault();
    const target = event.target;
    // @ts-ignore
    this.checkFormData(target).forEach((value, key) => {
      this.result[key] = value;
    });
    this._edit.editUserProfile(this.result).subscribe(res => {
      if (res.status) {
        UIkit.notification({
          message: '<span uk-icon=\'icon: check\'></span> Saved',
          status: 'success',
          timeout: 500
        });
        setTimeout(() => {
          location.reload();
        }, 1000);

      }
    });
  }

  checkFormData(target: any) {
    const formData = new FormData();
    formData.append('active', '1');
    if (target.querySelector('#login').value !== this.login) {
      formData.append('login', target.querySelector('#login').value); }
    if (target.querySelector('#email').value !== this.email) {
      formData.append('email', target.querySelector('#email').value); }
    if (target.querySelector('#name').value !== this.name) {
      formData.append('name', target.querySelector('#name').value); }
    if (target.querySelector('#lastName').value !== this.lastName) {
      formData.append('lastName', target.querySelector('#lastName').value); }
    if (target.querySelector('#isPrivate').checked !== this.isPrivate) {
      formData.append('isPrivate', target.querySelector('#isPrivate').checked); }
    if (target.querySelector('#enableMat').checked !== this.enableMat) {
      formData.append('enableMat', target.querySelector('#enableMat').checked); }
    if (target.querySelector('#description').value !== this.description) {
      formData.append('description', target.querySelector('#description').value); }
    if (target.querySelector('#birthDay').value !== this.birthDay) {
      formData.append('birthDay', target.querySelector('#birthDay').value); }
    if (this.avatar !== this.orgAv) {
      formData.append('avatar', this.avatar); }
    return formData;
  }
}
