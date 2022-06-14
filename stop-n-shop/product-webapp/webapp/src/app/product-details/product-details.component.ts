import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';
import { DealOfTheDayService } from '../services/deal-of-the-day.service';
import { ServiceImplService } from '../services/service-impl.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css'],
})
export class ProductDetailsComponent implements OnInit {
  constructor(
    private s: ServiceImplService,
    private service: ServiceService,
    private sanitizer: DomSanitizer,
    private route: Router,
    private dealoftheday: DealOfTheDayService,
    private userService: UserService
  ) {}

  p: number = 1;
  products: any = [];
  recommand: any = [];
  product: any;
  discount: any;
  userRes:any;
  discountPercentage: any;
  temp: any;
  flag: boolean = false;
  cartProduct: any;
  // userResponse!: any;

  ngOnInit(): void {
    // this.s.getProducts().subscribe(response=>{
    //   console.log(response);
    //   this.product=response;
    //     this.product.productImage='data:image/jpeg;base64,' +this.product.productImage;
    //     this.temp='data:image/jepg;base64,' +this.product.qrcode;
    //     this.product.qrcode= this.sanitizer.bypassSecurityTrustUrl(this.temp);
    //     this.discount=((this.product.mrp-this.product.sellingPrice)*100)/this.product.mrp;
    //     this.discountPercentage=Math.floor(this.discount);
    //   console.log(this.product)
    // },
    // (error)=>
    //   {
    //     console.log("Hay Error occored");
    //   }
    //   );

    this.getData();
    this.getDetailsOfProduct();
    this.getDoDproduct();
  }

  getDoDproduct() {
    this.dealoftheday.getDetail().subscribe(
      (response) => {
        console.log(response);
        this.product = response;
        this.cartProduct = response;
        this.product.productImage =
          'data:image/jpeg;base64,' + this.product.productImage;
        this.temp = 'data:image/jepg;base64,' + this.product.qrcode;
        console.log(this.product.brand);
        if (this.product.productQuantity < 40) {
          this.flag = true;
        }
        this.product.qrcode = this.sanitizer.bypassSecurityTrustUrl(this.temp);
        this.discount =
          ((this.product.mrp - this.product.sellingPrice) * 100) /
          this.product.mrp;
        this.discountPercentage = Math.floor(this.discount);
        console.log(this.product);
      },
      (error) => {
        console.log('Hay Error occored');
      }
    );

    this.getData();
  }
  cartitemquantity: any = 1;
  onShowLog(itemQuantity: any) {
    this.cartitemquantity = itemQuantity;
  }

  getDetailsOfProduct() {
    this.service.getDetail().subscribe(
      (response) => {
        console.log(response);
        this.product = response;
        this.cartProduct = response;
        this.product.productImage =
          'data:image/jpeg;base64,' + this.product.productImage;
        this.temp = 'data:image/jepg;base64,' + this.product.qrcode;
        console.log(this.product.brand);

        this.product.qrcode = this.sanitizer.bypassSecurityTrustUrl(this.temp);
        this.discount =
          ((this.product.mrp - this.product.sellingPrice) * 100) /
          this.product.mrp;
        this.discountPercentage = Math.floor(this.discount);
        console.log(this.product);
      },
      (error) => {
        console.log('Hay Error occored');
      }
    );

    this.getData();
  }

  getData() {
    this.service.getRecommandation().subscribe((resp: any) => {
      console.log('in productdetails' + resp);
      this.recommand = resp;

      for (var img = 0; img < this.recommand.length; img++) {
        this.recommand[img].productImage =
          'data:image/jpeg;base64,' + this.recommand[img].productImage;
        this.discount =
          ((this.recommand[img].mrp - this.recommand[img].sellingPrice) * 100) /
          this.recommand[img].mrp;
        // this.discountPercentage=Math.floor(this.discount);
        this.recommand[img].discount = Math.floor(this.discount);
      }
    });
  }
  addToCart() {
    const cart = {
      buyerEmail: localStorage.getItem('userDetail'),
      productList: [
        {
          productId: this.cartProduct.productId,
          productName: this.cartProduct.productName,
          sellingPrice: this.cartProduct.sellingPrice,
          productImage: this.cartProduct.productImage,
          productPrice: this.cartProduct.mrp,
          productQuantity: this.cartitemquantity,
          brand: this.cartProduct.brand,
          sellerEmail: this.cartProduct.sellerEmail,
        },
      ],
    };
    this.service.saveToCart(cart).subscribe((response) => {
      console.log(response);
      this.route.navigateByUrl('/navbar/mycart');
    });
  }

  email(product: any) {
    const body = {
      ProductName: product.productName,
      // Image:product.productImage,
      Brand: product.brand,
      ProductDescription: product.ProductDescription,
      Price: product.sellingPrice,
    };
    const data = {
      toEmail: this.shareForm.value.shareEmail,
      subject: 'ProductDetails',
      body: JSON.stringify(body),
    };
    this.s.email(data).subscribe((resp: any) => {
      console.log(resp);
      console.log('in emil');
      this.shareForm.reset();
    });
  }

  recommendDetail(data: any) {
    localStorage.setItem('productId', data.productId);
    localStorage.setItem('productCategory', data.productCategory);
    window.location.reload();
  }

  shareForm = new FormGroup({
    shareEmail: new FormControl('', [Validators.required]),
  });

  // userResponse: any;
  // username: any;
  // login() {
  //   let userId: any = localStorage.getItem('userDetail');
  //   console.log(userId);
  //   let username: any;
  //   this.service.fetchUserAddress().subscribe((res)=>{
  //     this.userRes=res
  //     username=this.userRes.userName;
  //   })
  //   console.log(username);
  //   this.userService.loginUser(userId, username).subscribe((data: any) => {
  //     this.route.navigate(['/navbar/chat', data.userId]);
  //   });
  // }
  userResponse!: any;
  username: any;
  login() {
    this.service.fetchUserAddress().subscribe((res) => {
      this.userResponse = res;
      this.username = this.userResponse.userName;
      this.userService
        .loginUser(localStorage.getItem('userDetail'), this.username)
        .subscribe((data: any) => {
          this.route.navigate(['/navbar/chat', data.userId]);
        });
      console.log(this.username);
    });
  }
}
