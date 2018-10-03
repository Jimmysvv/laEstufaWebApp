import { Component, OnInit } from '@angular/core';
import {CreateNewPostService} from './create-new-post.service';

@Component({
  selector: 'app-create-new-post',
  templateUrl: './create-new-post.component.html',
  styleUrls: ['./create-new-post.component.css'],
  providers: [ CreateNewPostService ]
})
export class CreateNewPostComponent implements OnInit {

  constructor(private _createNewPostService: CreateNewPostService) { }

  ngOnInit() {
  }

  createNewPost(event) {
    event.preventDefault();
    const target = event.target;
    const contents = target.querySelector('#contents').value;
    const authorId = 'pronto';
    const date = new Date();
    const hasImage = false;
    this._createNewPostService.createNewPost(contents, authorId, date, hasImage);
  }
}
