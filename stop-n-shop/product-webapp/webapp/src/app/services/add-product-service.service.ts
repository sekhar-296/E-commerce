import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { product } from '../products';

@Injectable({
  providedIn: 'root'
})
export class AddProductServiceService {

  constructor(private httpclient:HttpClient) { }

  //url=environment.url
   url='http://localhost:8080'
  addProducts(data:any){
    return this.httpclient.post(this.url+'/api/v8/addProduct',data);
  }

  getProductsBySeller(email:any){
    return this.httpclient.get(this.url+'/api/v8/product/seller/'+email);
  }
  updateProductStock(productId:any,quantity:any){
    return this.httpclient.patch(this.url+'/api/v8/updateProduct/'+productId+'/'+quantity,productId,quantity);
  }

  updateProductStockInRecommendation(productId:any,quantity:any){
    return this.httpclient.patch(this.url+'/api/v9/updateProduct/'+productId+'/'+quantity,productId,quantity);
  }
}
