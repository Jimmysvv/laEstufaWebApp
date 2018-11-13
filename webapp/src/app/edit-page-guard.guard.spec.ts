import {inject, TestBed} from '@angular/core/testing';
import {EditPageGuardGuard} from './edit-page-guard.guard';

describe('EditPageGuardGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EditPageGuardGuard]
    });
  });

  it('should ...', inject([EditPageGuardGuard], (guard: EditPageGuardGuard) => {
    expect(guard).toBeTruthy();
  }));
});
