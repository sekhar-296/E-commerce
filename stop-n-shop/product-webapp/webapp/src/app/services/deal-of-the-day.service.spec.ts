import { TestBed } from '@angular/core/testing';

import { DealOfTheDayService } from './deal-of-the-day.service';

describe('DealOfTheDayService', () => {
  let service: DealOfTheDayService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DealOfTheDayService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
