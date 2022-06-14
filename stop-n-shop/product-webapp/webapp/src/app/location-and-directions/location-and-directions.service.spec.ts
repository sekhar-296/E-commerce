import { TestBed } from '@angular/core/testing';

import { LocationAndDirectionsService } from './location-and-directions.service';

describe('LocationAndDirectionsService', () => {
  let service: LocationAndDirectionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LocationAndDirectionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
