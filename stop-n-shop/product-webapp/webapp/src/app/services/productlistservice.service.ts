import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';



@Injectable({
  providedIn: 'root'
})
export class ProductlistserviceService {
  

  constructor(private http: HttpClient) {
    
   }

   //url=environment.url
   url='http://localhost:8080'
   productId:any;

  //  url:string="http://localhost:9000/api/v8/products";

    getProductList(){
      return this.http.get<any>(this.url+'/api/v8/products');
    }

    url1:string=this.url+"/api/v8/getProduct/{productId}";
    getProductById(){
      return this.http.get<any>(this.url1);
    }
    
    productDetail(data:any){
      console.log(" productId ");
      console.log(data);
      this.productId=data;
    }
}
