import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegisterShopService {
  constructor(private httpClient:HttpClient) { }

 // url=environment.url;

   url='http://localhost:8080'
  getCoordinates(address:any){
    return this.httpClient.get<any>("https://api.geoapify.com/v1/geocode/search?text="+address+"&apiKey=ec14a8297f984672a461337f055477c2");  
  }

  addShop(data:any){
    return this.httpClient.post(this.url+'/api/v11/shop',data);
  }

  getUserDetails(email:any){
    return this.httpClient.get<any>(this.url+"/api/v1/user/"+email);  
  }
}
