import { TestBed } from '@angular/core/testing';

import { ShopsListService } from './shops-list.service';

describe('ShopsListService', () => {
  let service: ShopsListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShopsListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
