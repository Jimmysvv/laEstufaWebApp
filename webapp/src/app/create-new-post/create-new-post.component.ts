import {Component, OnInit, ViewChild} from '@angular/core';
import {CreateNewPostService} from './create-new-post.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-new-post',
  templateUrl: './create-new-post.component.html',
  styleUrls: ['./create-new-post.component.css'],
  providers: [ CreateNewPostService ]
})
export class CreateNewPostComponent implements OnInit {

  url: string;
  reader: any;
  contents: string;
  authorId: string;
  // fileName: string;
  size: number;
  file: File;
  counter: number = 200;

  constructor(private _createNewPostService: CreateNewPostService,
              private _router: Router) { }

  ngOnInit() {
    this.url = '';
  }

  @ViewChild('formP') formp;
  @ViewChild('formW') formw;

  onSelectFile(event) {
    this.url = '';
    if (event.target.files && event.target.files[0]) {
      this.reader = new FileReader();
      this.reader.readAsDataURL(event.target.files[0]);
      this.reader.onload = (event) => {
        this.url = event.target.result;
      };
      this.file = event.target.files[0];
    }
  }

  clearP(event) {
    event.preventDefault();
    this.formp.nativeElement.reset();
    this.url = '';
  }

  clearW(event) {
    event.preventDefault();
    this.formw.nativeElement.reset();
  }

  createNewPost(event) {
    event.preventDefault();
    const target = event.target;
    target.querySelector('#contents') != null ? this.size = target.querySelector('#contents').value.length : this.size = 0;
    this.size <= 0 ? this.contents = null : this.contents = target.querySelector('#contents').value;
    this.authorId = localStorage.getItem('UserId');
    const date = new Date();
    if (this.url.length <= 0 && this.contents !== null) {
      this.uploadQuotePost(this.contents, this.authorId, date);
    } else if (this.url.length > 0 && this.contents === null) {
      this.uploadImagePost(this.contents, this.authorId, date, this.url);
    }
    this._router.navigate(['']);
  }

  uploadQuotePost(contents, authorId, date) {
    this._createNewPostService.createNewPost(contents, authorId, date, null)
          .subscribe(mes => {
            console.log(mes.message);
        });
  }

  uploadImagePost(contents, authorId, date, url) {
    this._createNewPostService.createNewPost(contents, authorId, date, url)
      .subscribe(mes => {
        console.log(mes.message);
      });

    /* uploads file into server and name to db
    const fd = new FormData();
    fd.append('file', this.file);
    this._createNewPostService.uploadFile(fd)
      .subscribe(res => {
        this.fileName = res.message;
        if (res.success) {
          this._createNewPostService.createNewPost(contents, authorId, date, this.fileName)
            .subscribe(mes => {
              console.log(mes.message);
            });
        }
      });
      */
  }

  count(event) {
    event.preventDefault();
    let size = event.target.value.length;
    this.counter = 200 - size;
  }
}
