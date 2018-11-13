import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {SearchService} from './search.service';
import {fromEvent} from 'rxjs';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  search: boolean;
  searchValue: string;
  resultArea: boolean;
  searchData: any = [];
  showSpinner: boolean;
  showError: boolean;
  type = 'people';
  @ViewChild('search') searchBar;
  @ViewChild('obsTab') searchTab;

  constructor(private _route: ActivatedRoute,
              private _searchService: SearchService) {
    this._route.params.subscribe(val => {
      this.searchValue = this._route.snapshot.paramMap.get('searchValue');
    });
    this._route.queryParams.subscribe(params => {
      if (params.search) {
        this.search = params.search;
      }
    });
  }

  ngOnInit() {
    if (this.search) {
      this.searchBar.nativeElement.style.borderColor = '#39f';
      this.searchBar.nativeElement.style.boxShadow = '0 0 10px #39f';
      setTimeout(() => {
        this.searchBar.nativeElement.style.borderColor = '';
        this.searchBar.nativeElement.style.boxShadow = '';
      }, 2000);
    }
    if (this.searchValue) {
      this.searchBar.nativeElement.value = this.searchValue;
      this.showResultArea(this.searchValue);
    }
    const obs = fromEvent(this.searchBar.nativeElement, 'keyup')
      .pipe(
      map((e: any) => e.target.value),
      debounceTime(1000),
      distinctUntilChanged());
    const obsTab = fromEvent(this.searchTab.nativeElement, 'click')
      .pipe(
        map((e: any) => e),
        debounceTime(1000),
        distinctUntilChanged());
    obs.subscribe(val => {
      this.searchValue = val;
      this.showResultArea(val);
    });
    obsTab.subscribe(val => {
      this.type = val.current.innerText.toLocaleLowerCase();
      this.showResultArea(this.searchValue);
    });
  }

  showResultArea(value) {
    if (value.length > 0) {
      this.resultArea = true;
      this.showSpinner = this.resultArea;
      this._searchService.getSearchResult(value, this.type)
        .subscribe(res => {
          this.searchData = res;
          this.showError = this.searchData.length <= 0;
          this.showSpinner = false;
        }, () => {
          this.showError = true;
          this.showSpinner = false;
        });
    } else {
      this.resultArea = false;
    }
  }

  atob (val) {
    return atob(val);
  }
}
