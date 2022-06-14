import { TestBed } from '@angular/core/testing';

import { ShortestRouteService } from './shortest-route.service';

describe('ShortestRouteService', () => {
  let service: ShortestRouteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShortestRouteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
