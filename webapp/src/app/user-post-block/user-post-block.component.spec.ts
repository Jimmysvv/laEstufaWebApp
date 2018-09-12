import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPostBlockComponent } from './user-post-block.component';

describe('UserPostBlockComponent', () => {
  let component: UserPostBlockComponent;
  let fixture: ComponentFixture<UserPostBlockComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserPostBlockComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPostBlockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
