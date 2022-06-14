import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl } from '@angular/forms';
import {
  MAT_TOOLTIP_DEFAULT_OPTIONS,
  TooltipPosition,
} from '@angular/material/tooltip';
import { ActivatedRoute, Router } from '@angular/router';
import { toInteger } from '@ng-bootstrap/ng-bootstrap/util/util';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs/internal/Subject';
import Swal from 'sweetalert2';
import { ServiceService } from '../service.service';
import { AddProductServiceService } from '../services/add-product-service.service';
import { MycartService } from '../services/mycart.service';
import { Payment, UpdatePayment } from './model/type';

@Component({
  selector: 'app-mycart',
  templateUrl: './mycart.component.html',
  styleUrls: ['./mycart.component.css'],
  // providers: [{provide: MAT_TOOLTIP_DEFAULT_OPTIONS, useValue: myCustomTooltipDefaults}],
})
export class MycartComponent implements OnInit {
  p: number = 1;
  cart: any;
  total: any;
  productObj: any;
  email: any;
  product: any;
  calculate: any;
  purchasedQuantity: any;
  flag: boolean = false;
  discount: any;
  // total:any;
  discountPercentage: any;
  totalItemsIncart: number = 0;
  totalBillAmount: number = 0;
  totalDiscountonMRP: number = 0;
  positionOptions: TooltipPosition[] = [
    'after',
    'before',
    'above',
    'below',
    'left',
    'right',
  ];
  position = new FormControl(this.positionOptions[0]);

  onShowLog(itemIndex: any, itemQuantity: any) {
    this.cart[itemIndex].productQuantity = itemQuantity;
    this.calculateTotal();
  }

  constructor(
    private service: ServiceService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private mycart: MycartService,
    private addproductservice: AddProductServiceService
  ) {}

  ngOnInit(): void {
    // this.getDetailsFromCart(localStorage.getItem("userDetail"));
    // this.getDetailsFromCart();
    this.getDetailsFromCart();
  }

  getDetailsFromCart() {
    this.service.getCartDetails().subscribe((resp: any) => {
      console.log(resp);

      this.cart = resp;

      this.flag = false;

      // this.calculate=Math.floor(this.total);
      // for(var img=0;img<this.cart.length;img++){
      //   this.cart[img].productImage='data:image/jpeg;base64,'+this.cart[img].productImage;
      // }
      // this.total=(this.product.productQuantity*this.product.productPrice);
    });
  }

  calculateTotal() {
    this.total = 0;
    this.totalItemsIncart = 0;
    this.totalBillAmount = 0;
    this.totalDiscountonMRP = 0;
    this.discountPercentage = 0;
    for (const product of this.cart) {
      this.totalItemsIncart =
        Number(this.totalItemsIncart) + Number(product.productQuantity);
      this.total += product.productQuantity * product.productPrice;
      this.totalBillAmount += product.productQuantity * product.sellingPrice;
      this.totalDiscountonMRP = this.total - this.totalBillAmount;
      this.discountPercentage = (this.totalDiscountonMRP / this.total) * 100;
      //  this.discount=((this.product.productPrice-this.product.sellingPrice)*100)/this.product.productPrice;
    }
    return this.total;
  }

  deleteItem(items: any, index: any) {
    this.cart.splice(index, 1);
    console.log(items);
    console.log(items.productId);

    this.service
      .deleteItems(items.productId)
      .subscribe((resp: any) => console.log(resp));
  }

  deleteAll() {
    this.service
      .deleteAllProducts(localStorage.getItem('userDetail'))
      .subscribe((resp: any) => {
        console.log(resp);

        this.cart = resp;
        console.log(this.cart);
      });
    // window.location.reload();
  }
  userAddress:any;
  checkAddress(){
    this.service.fetchUserAddress().subscribe((resp)=>{
        this.userAddress=resp
        console.log(this.userAddress);
        
        if(this.userAddress.address='' || this.userAddress.address==null){
          this.router.navigateByUrl('/navbar/profile')
        }
        else{
          this.paymentStart()
          
        }
        
    })
  }

  loggedInUser: any = localStorage.getItem('userDetail');
  tempOrderId!: string;
  paymentId!: string;
  //note: payement start method
  paymentStart = () => {
    let payload: Payment = {
      amt: this.totalBillAmount + '',
      name: '',
      email: this.loggedInUser,
    };

    if (payload.amt == '')
      Swal.fire({
        icon: 'error',
        title: 'amount not present',
      });

    this.mycart.paymentRequest(payload).subscribe(
      (response: any) => {
        if (response.status == 'created') {
          this.tempOrderId = response.id;
          let options = {
            key: 'rzp_test_aDaVWiyiQ8fT6t',
            amount: response.amount,
            currency: 'INR',
            name: 'stopnShop',
            description: 'payment gateway',
            image: './assets/appLogo.png',
            order_id: response.id,
            handler: (response: any) => {
              console.log(response);
              this.paymentId = response.razorpay_payment_id;
              this.updatePaymentDetail(
                this.tempOrderId,
                this.paymentId,
                'paid'
              );
              this.updateOrderDetail(this.cart, this.tempOrderId);
            },
            prefill: {
              name: '',
              email: localStorage.getItem('userDetail'),
              contact: '',
            },
            theme: {
              color: '#3399cc',
            },
            success: true,
          };
          const rzp = new this.mycart.nativeWindow.Razorpay(options);
          rzp.on('payment.failed', (_response: any) => {
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Payment Failed 😢',
            });
          });

          rzp.open();
        }
      },
      (_error: any) => {
        console.log(_error);
      }
    );
  };
  updatePaymentDetail = (order_id: any, payement_id: any, status: any) => {
    let details: UpdatePayment = {
      order_id: order_id,
      payment_id: payement_id,
      status: status,
    };
    this.mycart.updatePaymentDetails(details).subscribe((response: any) => {
      console.log(response.text());

      Swal.fire(
        `Hey ${localStorage.getItem('userDetail')}Good job!`,
        'You payment is successful!',
        'success'
      );
    }),
      (error: any) => {
        Swal.fire({
          icon: 'error',
          title: `Hey ${localStorage.getItem('userDetail')}`,
          text: 'payment received but unable to process data. StopnShop will contact soon! 😢',
        });
      };
  };
  prodObj: any = {
    productId: '1223a',
    productName: '',
    productImage: null,
    productPrice: null,
    productQuantity: 0,
    brand: null,
    sellingPrice: null,
    sellerEmail: null,
  };
  prodList: any = [];
  updateOrderDetail = (carts: any, id: string) => {
    console.log(carts);
    carts.forEach((element: any) => {
      // this.prodObj.productId = element.productId;
      // this.prodObj.productName = element.productName;
      // this.prodObj.productImage = element.productImage;
      // this.prodObj.productPrice = element.productPrice;
      // this.prodObj.productQuantity = element.productQuantity;
      // this.prodObj.brand = element.brand;
      // this.prodObj.sellingPrice = element.sellingPrice;
      // this.prodObj.sellerEmail = element.sellerEmail;
      // this.prodList.push(this.prodObj);
       this.prodList.push(element);
    }
    
    );

    this.prodList.forEach((a: any) => {
      console.log('list of products');
      console.log(a);
    });

    this.mycart.updateOrderDetails(this.prodList, id).subscribe((data: any) => {
      for (var i = 0; i < this.cart.length; i++) {
        this.purchasedQuantity = this.cart[i].productQuantity;
        var id = this.cart[i].productId;
        this.addproductservice
          .updateProductStock(id, this.purchasedQuantity)
          .subscribe((resp) => {
            console.log('updated stock' + resp);
          });
        this.addproductservice
          .updateProductStockInRecommendation(id, this.purchasedQuantity)
          .subscribe((resp) => {
            console.log('updated in recommendation' + resp);
          });
      }
      console.log('items bought ' + this.cart[0].productQuantity);
      this.cart.splice(0, this.cart.length);
      this.deleteAll();
    });
  };
}
