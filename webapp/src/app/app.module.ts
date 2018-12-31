import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { UserPostBlockComponent } from './user-post-block/user-post-block.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { CreateNewPostComponent } from './create-new-post/create-new-post.component';
import { ServiceWorkerModule } from '@angular/service-worker';
import {environment} from '../environments/environment';
import {AuthGuard} from './auth.guard';
import {AuthService} from './auth.service';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import {ImageCropperModule} from 'ngx-image-cropper';
import {EditPageGuardGuard} from './edit-page-guard.guard';
import { DiscoverComponent } from './discover/discover.component';
import { SearchComponent } from './search/search.component';
import { SafePipe } from './safe.pipe';
import { FollowingComponent } from './following/following.component';
import { MoreComponent } from './more/more.component';
import { LikeComponent } from './like/like.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    UserPostBlockComponent,
    LoginComponent,
    RegisterComponent,
    UserProfileComponent,
    CreateNewPostComponent,
    EditProfileComponent,
    DiscoverComponent,
    SearchComponent,
    SafePipe,
    FollowingComponent,
    MoreComponent,
    LikeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ImageCropperModule,
    RouterModule.forRoot([
      {
        path: 'user/:login',
        component: UserProfileComponent
      },
      {
        path: 'register',
        component: RegisterComponent
      },
      {
        path: 'signin',
        component: LoginComponent
      },
      {
        path: 'create',
        component: CreateNewPostComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'discover',
        component: DiscoverComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'posts/:postId',
        component: UserPostBlockComponent
      },
      {
        path: 'edit/:login',
        component: EditProfileComponent,
        canActivate: [AuthGuard, EditPageGuardGuard]
      },
      {
        path: 'search/:searchValue',
        component: SearchComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'settings',
        component: UserPostBlockComponent,
        canActivate: [AuthGuard]
      },
      {
        path: '',
        component: UserPostBlockComponent
      }
    ]),
    ServiceWorkerModule.register('/ngsw-worker.js', {enabled: environment.production})
  ],
  providers: [AuthService, AuthGuard, EditPageGuardGuard],
  bootstrap: [AppComponent, HeaderComponent, FooterComponent]
})
export class AppModule { }
