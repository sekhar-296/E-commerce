import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { of } from 'rxjs';
import { environment } from 'src/environments/environment';
// import { Observable } from 'rxjs';
import { product } from '../products';

@Injectable({
  providedIn: 'root'
})
export class ServiceImplService {

  constructor(private http:HttpClient) { }

  //url=environment.url
   url='http://localhost:8080'
  id:any='SnS-1028442016';
  // url:string="http://localhost:9000/api/v8/getProduct/"+'SnS-1028442016';

  getProducts()
  {
    return this.http.get(this.url+'/api/v8/getProduct/'+localStorage.getItem("productId"));
  }

  email(data:any)
  {
    return this.http.post<any>(this.url+'/api/v4/email', data)
    
  }

}
