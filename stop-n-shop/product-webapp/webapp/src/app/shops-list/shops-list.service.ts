import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ShopsListService {

  //url=environment.url
   url='http://localhost:8080'
  constructor(private httpClient:HttpClient) { }
  url1:string=this.url+'/api/v11/shop';
  shopUrl:string=""
  
  getShopDetail(){
    return this.httpClient.get<any>(this.url1);  
  }

  shopIdDataWithUrl(data:any){
    this.shopUrl=this.shopUrl+data;
    console.log(this.shopUrl);
  }

  locationData:any;
  modelLocationData(location:any){
    this.locationData=location;
  }
 
}
