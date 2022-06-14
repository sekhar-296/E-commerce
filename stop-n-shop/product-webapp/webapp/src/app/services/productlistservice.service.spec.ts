import { TestBed } from '@angular/core/testing';

import { ProductlistserviceService } from './productlistservice.service';

describe('ProductlistserviceService', () => {
  let service: ProductlistserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductlistserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
