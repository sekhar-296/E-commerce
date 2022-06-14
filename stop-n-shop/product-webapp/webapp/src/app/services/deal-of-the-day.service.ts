import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class DealOfTheDayService{
  //url=environment.url

   url='http://localhost:8080'

  getProductList(){
    return this.http.get<any>(this.url+'/api/v8/products');
  }
   
  getDetail()
  {
   return this.http.get(this.url+'/api/v8/getProduct/'+localStorage.getItem("productId"));
  }
  
  constructor( private http:HttpClient) { }


}
