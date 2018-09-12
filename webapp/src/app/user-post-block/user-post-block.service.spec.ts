import { TestBed, inject } from '@angular/core/testing';

import { UserPostBlockService } from './user-post-block.service';

describe('UserPostBlockService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserPostBlockService]
    });
  });

  it('should be created', inject([UserPostBlockService], (service: UserPostBlockService) => {
    expect(service).toBeTruthy();
  }));
});
