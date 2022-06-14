import { TestBed } from '@angular/core/testing';

import { ServiceImplService } from './service-impl.service';

describe('ServiceImplService', () => {
  let service: ServiceImplService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceImplService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
