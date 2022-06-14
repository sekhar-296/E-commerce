import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Payment, UpdatePayment } from '../mycart/model/type';
function _window(): any {
  // return the global native browser window object
  return window;
}
@Injectable({
  providedIn: 'root',
})
export class MycartService {
  constructor(private httpClient: HttpClient) {}
  //url=environment.url
   url='http://localhost:8080'
  paymentRequest(details: Payment): Observable<Object> {
    return this.httpClient.post(this.url+'/api/v7/create', {
      amt: details.amt,
      name: details.name,
      email: details.email,
    });
  }
  updatePaymentDetails( details: UpdatePayment): Observable<Object> {
    return this.httpClient.patch(this.url+'/api/v7/update', {
      order_id: details.order_id,
      payment_id: details.payment_id,
      status: details.status,
    });
  }

  updateOrderDetails(product: any, orderid: string) {
    return this.httpClient.patch(this.url+
      '/api/v7/productInformation?orderId=' + orderid,
      product
    );
  }
  get nativeWindow(): any {
    return _window();
  }
}
