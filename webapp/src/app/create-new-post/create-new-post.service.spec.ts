import { TestBed, inject } from '@angular/core/testing';

import { CreateNewPostService } from './create-new-post.service';

describe('CreateNewPostService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CreateNewPostService]
    });
  });

  it('should be created', inject([CreateNewPostService], (service: CreateNewPostService) => {
    expect(service).toBeTruthy();
  }));
});
