import { Component, OnInit } from '@angular/core';
import { ShopsListService } from '../shops-list/shops-list.service';

@Component({
  selector: 'app-location-model',
  templateUrl: './location-model.component.html',
  styleUrls: ['./location-model.component.css']
})
export class LocationModelComponent implements OnInit {

  constructor(private shopService:ShopsListService) { }
  lat:any;
  lon:any;
  ngOnInit(): void {
    this.getLocationData();
      
    
  }
  getLocationData() {
    this.lat=this.shopService.locationData.latitude;
    this.lon=this.shopService.locationData.longitude;
  }
}
 

