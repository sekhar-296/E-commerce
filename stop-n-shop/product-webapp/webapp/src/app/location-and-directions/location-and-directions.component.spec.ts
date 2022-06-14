import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocationAndDirectionsComponent } from './location-and-directions.component';

describe('LocationAndDirectionsComponent', () => {
  let component: LocationAndDirectionsComponent;
  let fixture: ComponentFixture<LocationAndDirectionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LocationAndDirectionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LocationAndDirectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
