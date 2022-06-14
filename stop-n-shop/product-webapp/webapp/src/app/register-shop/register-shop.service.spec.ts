import { TestBed } from '@angular/core/testing';

import { RegisterShopService } from './register-shop.service';

describe('RegisterShopService', () => {
  let service: RegisterShopService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegisterShopService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
