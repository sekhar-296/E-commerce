import { getSupportedInputTypes } from '@angular/cdk/platform';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { OrderhistoryComponent } from '../orderhistory/orderhistory.component';

@Injectable({
  providedIn: 'root'
})
export class OrderhistoryService {

constructor(private http: HttpClient) {

  }
  //url=environment.url
   url='http://localhost:8080'

  

   productId:any;
  //  url:string="http://localhost:9542/api/v7/information/";
    getOrderList(){
      return this.http.get<any>(this.url+'/api/v7/information/'+localStorage.getItem('userDetail'));
    }
    // url1:string="http://localhost:9542/api/v7/";
    getOrderById(id:any){
      return this.http.get<any>(this.url+'/api/v7/'+id);
    }

   
 
}
