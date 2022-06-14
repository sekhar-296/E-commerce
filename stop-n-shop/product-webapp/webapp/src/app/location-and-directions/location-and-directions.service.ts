import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocationAndDirectionsService {

  
  constructor(private httpClient:HttpClient) { }

 

  getCoordinates(address:any){
    return this.httpClient.get<any>("https://api.geoapify.com/v1/geocode/search?text="+address+"&apiKey=ec14a8297f984672a461337f055477c2");  
  }

  url: string="";
  requestUrl:any;
  getDirections(arr:any){
    for (let i = 0; i < arr.length; i=i+2) {
      this.url=this.url+arr[i]+","+arr[i+1]+";";
    }
    const editedText = this.url.slice(0, -1);
    this.requestUrl="https://api.mapbox.com/directions/v5/mapbox/driving/"+editedText+"?geometries=geojson&access_token=pk.eyJ1IjoiZGluZXNoc2VuZ290dHV2ZWwiLCJhIjoiY2wzajN1MWNsMGFobzNocWltNmJqNGdhZCJ9.0JWXQClDLbSeO3PlHamMxg";
    return this.httpClient.get<any>(this.requestUrl);  
  }
  
  getAddressFromCoordinates(lat:any, lon:any){
    return this.httpClient.get<any>("https://api.geoapify.com/v1/geocode/reverse?lat="+lat+"&lon="+lon+"&format=json&apiKey=ec14a8297f984672a461337f055477c2");  
  }

}
