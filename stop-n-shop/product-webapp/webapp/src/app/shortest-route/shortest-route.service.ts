import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ShortestRouteService {

  constructor(private httpClient:HttpClient) { }
  //url=environment.url
   url='http://localhost:8080'
 
  url1:string=this.url+"/api/v7/";
  getByOrderId(orderId:any){
    return this.httpClient.get<any>(this.url1+orderId);  
  }

  url2:string=this.url+"/api/v11/shopbyemail/";
  getAddressByEmailId(emailId:any){
    return this.httpClient.get<any>(this.url2+emailId);  
  }

  getCoordinates(address:any){
    return this.httpClient.get<any>("https://api.geoapify.com/v1/geocode/search?text="+address+"&apiKey=ec14a8297f984672a461337f055477c2");  
  }

  url5: string="";
  requestUrl:any;
  getDirections(arr:any){
    console.log(arr);
    for (let i = 0; i < arr.length; i=i+2) {
      this.url5=this.url5+arr[i]+","+arr[i+1]+";";
      console.log(this.url5);
    }
   
    const editedText = this.url5.slice(0, -1);
    this.requestUrl="https://api.mapbox.com/directions/v5/mapbox/driving/"+editedText+"?geometries=geojson&access_token=pk.eyJ1IjoiZGluZXNoc2VuZ290dHV2ZWwiLCJhIjoiY2wzajN1MWNsMGFobzNocWltNmJqNGdhZCJ9.0JWXQClDLbSeO3PlHamMxg";
    return this.httpClient.get<any>(this.requestUrl);  
  }


  getUserDetails(email:any){
    return this.httpClient.get<any>(this.url+"/api/v1/user/"+email);  
  }
}
