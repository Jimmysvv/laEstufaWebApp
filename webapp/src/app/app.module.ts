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

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    UserPostBlockComponent,
    LoginComponent,
    RegisterComponent,
    UserProfileComponent,
    CreateNewPostComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
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
        component: UserPostBlockComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'edit',
        component: UserPostBlockComponent,
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
  providers: [AuthService, AuthGuard],
  bootstrap: [AppComponent, HeaderComponent, FooterComponent]
})
export class AppModule { }
