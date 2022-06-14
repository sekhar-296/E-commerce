import { Component, OnInit } from '@angular/core';
import { LocationAndDirectionsService } from './location-and-directions.service';

@Component({
  selector: 'app-location-and-directions',
  templateUrl: './location-and-directions.component.html',
  styleUrls: ['./location-and-directions.component.css']
})
export class LocationAndDirectionsComponent implements OnInit {

  constructor(private locationService :LocationAndDirectionsService) { }

  ngOnInit(): void {
  }

 latlng=[];
 address:any;
 arr:any=[];
 markers:any=[];
 

 record:any;
  findCoordinates():void{
    this.locationService.getCoordinates(this.address).subscribe( response=>{
    this.record=response;
   
    this.arr.push( (this.record.features[0].properties.lon))
    this.arr.push( (this.record.features[0].properties.lat))
    //for adding marker in map
    const last2 = this.arr.slice(-2);
    this.markers.push(last2);

    this.findDirections(this.arr);
    });
  }

    dirctionRecord:any;
    findDirections(arr:any):void{
    this.locationService.getDirections(this.arr).subscribe( response=>{
    this.dirctionRecord=response.routes[0].geometry.coordinates;
    this.latlng=this.dirctionRecord;
    });
   }

   reset():void{
      this.arr=[];
      this.markers=[];
      this.latlng=[];
   }

   latitude!: number;
   longitude!: number;

   onMapClicked(event: any){
      console.table(event.coords);
       this.latitude = event.coords.lat;
      this.longitude = event.coords.lng;
   }

   
   reverseGeocoding():void{
     this.locationService.getAddressFromCoordinates(this.latitude,this.longitude).subscribe( response=>{
      this.address=response.results[0].formatted;
      });
   }
  
   reverseGeocodeCoordinatesinserting(){
    this.arr.push( this.longitude)
    this.arr.push( (this.latitude))
    //for adding marker in map 
    const last2 = this.arr.slice(-2);
    this.markers.push(last2);
    this.findDirections(this.arr);
    this.findDirections(this.arr);
   }
 
   componentDidMount(){
    navigator.geolocation.getCurrentPosition(
                        (position) => {
                                   console.log(position);
                                   this.latitude = position.coords.latitude;
                                   this.longitude =position.coords.longitude;
                                      })
    }

}


