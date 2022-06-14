import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrdersummaryService {

  constructor() { }

 // url=environment.url
   url='http://localhost:8080'
  // getOrderHistory(){
  //   // return this.http.get<any>(this.url);
  // }

 orders = [
   {
     
     "productImage":"/assets/maggi.jpg",
     "productName": "Colgate",
     "productPrice":"₹25",
     "productQuantity":1,
     "deliveredto":"Sanath",
     "deliverydate":"Friday May 30",
     "paymentmethod":"Cash On Delivery",
     "address":"Sarakki Building, Jayanagara 9th Block, Bangalore",
     "rating": 4,
     "deliverycharge": "₹60"
     

   },
   {
    "productImage":"/assets/pepsodent.png",
    "productName": "Colgate",
    "productPrice":"₹80",
    "productQuantity":1,
    "deliveredto":"Sanath",
    "deliverydate":"Friday May 30",
    "paymentmethod":"Cash On Delivery",
    "address":"Sarakki Building, Jayanagara 9th Block, Bangalore",
    "rating": 4,
    "deliverycharge": "₹60",
    
   },
    {   
   "productImage":"/assets/colgate.png",
   "productName": "Colgate",
   "productPrice":"₹120",
   "productQuantity":1,
   "deliveredto":"Sanath",
   "deliverydate":"Friday May 30",
   "paymentmethod":"Cash On Delivery",
   "address":"Sarakki Building, Jayanagara 9th Block, Bangalore",
   "rating": 4,
   "deliverycharge": "Free"
  },
]



  getOrders() {
    return this.orders;
   }
}
