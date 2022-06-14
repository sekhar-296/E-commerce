import { Component, OnInit ,Input} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from '../service.service';
import { ProductlistserviceService } from '../services/productlistservice.service';
import { ShopsListService } from '../shops-list/shops-list.service';
import { NgZone } from '@angular/core';
import { AddProductServiceService } from '../services/add-product-service.service';
import { CookieService } from 'ngx-cookie-service';

 declare const annyang: any;

@Component({
  selector: 'app-productlist',
  templateUrl: './productlist.component.html',
  styleUrls: ['./productlist.component.css']
})
export class ProductlistComponent implements OnInit {

productList:any=[];
 p:number=1
 email:any;
 product:any;
  discount:any;
  discountPercentage:any;
  temp:any;
  cartProduct:any;

  constructor(private productlistserviceService: ProductlistserviceService, private router:Router, private shopListService : ShopsListService,
    private service:ServiceService,private route:ActivatedRoute,private ngZone: NgZone,private cookie:CookieService,
    private productservice:AddProductServiceService) {
   }


  //  initializeVoiceRecognitionCallback(): void {
	// 	annyang.addCallback('error', (err:any) => {
  //     if(err.error === 'network'){
  //       this.voiceText = "Internet is require";
  //       annyang.abort();
  //       this.ngZone.run(() => this.voiceActiveSectionSuccess = true);
  //     } else if (this.voiceText === undefined) {
	// 			this.ngZone.run(() => this.voiceActiveSectionError = true);
	// 			annyang.abort();
	// 		}
	// 	});

	// 	annyang.addCallback('soundstart', (res:any) => {
  //     this.ngZone.run(() => this.voiceActiveSectionListening = true);
	// 	});

	// 	annyang.addCallback('end', () => {
  //     if (this.voiceText === undefined) {
  //       this.ngZone.run(() => this.voiceActiveSectionError = true);
	// 			annyang.abort();
	// 		}
	// 	});

	// 	annyang.addCallback('result', (userSaid:any) => {
	// 		this.ngZone.run(() => this.voiceActiveSectionError = false);

	// 		let queryText: any = userSaid[0];

	// 		annyang.abort();
      
  //     this.voiceText = queryText;

	// 		this.ngZone.run(() => this.voiceActiveSectionListening = false);
  //     this.ngZone.run(() => this.voiceActiveSectionSuccess = true);
	// 	});
	// }

	// startVoiceRecognition(): void {
  //   this.voiceActiveSectionDisabled = false;
	// 	this.voiceActiveSectionError = false;
	// 	this.voiceActiveSectionSuccess = false;
  //   this.voiceText = undefined;

	// 	if (annyang) {
	// 		let commands = {
	// 			'demo-annyang': () => { }
	// 		};

	// 		annyang.addCommands(commands);

  //     this.initializeVoiceRecognitionCallback();

	// 		annyang.start({ autoRestart: false });
	// 	}
	// }

	// closeVoiceRecognition(): void {
  //   this.voiceActiveSectionDisabled = true;
	// 	this.voiceActiveSectionError = false;
	// 	this.voiceActiveSectionSuccess = false;
	// 	this.voiceActiveSectionListening = false;
	// 	this.voiceText = undefined;

	// 	if(annyang){
  //     annyang.abort();
  //   }
	// }







  getProductData():void{  
    
    this.productlistserviceService.getProductList().subscribe((response:any)=>{
      // console.log(response);
      response.forEach((element:any)=> {
      this.productList.push(element) 
         
      for(var i=0;i<this.productList.length;i++){
        this.productList[i].image='data:image/jpeg;base64,' +this.productList[i].productImage;  
        
        this.discount=(((this.productList[i].mrp)-(this.productList[i].sellingPrice))*100)/this.productList[i].mrp; 
        this.productList[i].discountPercentage=Math.floor(this.discount); 
      }
      // console.log(this.productList)  
      });
    })
  }

  saveData(data:any){
    localStorage.setItem('productId', data.productId);
    localStorage.setItem('productCategory', data.productCategory);
    localStorage.setItem('productCity', data.city);
    if(localStorage.getItem("userRole")!='"seller"'){
      this.router.navigateByUrl("/navbar/productDetails")
    }

  }
   
  
  productId:any;
    myFunction(productId:any){
      this.productId=this.productlistserviceService.productDetail;
    }

    productsBySeller(email:any){
      this.productservice.getProductsBySeller(email).subscribe((response:any)=>{
        response.forEach((product:any)=>{
          this.productList.push(product)
          for(var i=0;i<this.productList.length;i++){
            this.productList[i].image='data:image/jpeg;base64,' +this.productList[i].productImage;  
            
            this.discount=(((this.productList[i].mrp)-(this.productList[i].sellingPrice))*100)/this.productList[i].mrp; 
            this.productList[i].discountPercentage=Math.floor(this.discount); 
          }
        })
      })
    }

  ngOnInit(): void {
    if(localStorage.getItem("userRole")==='"seller"'){
        this.productsBySeller(localStorage.getItem("userDetail"));
    }
    else{
      if(localStorage.getItem("flag")=="shopProducts"){
        console.log("user is a buyer with shopping from shop");
        localStorage.setItem('flag','undefined')
        this.productsBySeller(localStorage.getItem("shopselleremail"))
      }
      else{
        console.log("user is a buyer");
        this.getProductData();
      }
    }
    
    // this.route.queryParams.subscribe(p=>{
    //   this.service.fetchToken(p['code'],p['state']).subscribe(a=>{
    //     this.email=a;
    //     localStorage.setItem("token",this.email.map.accessToken)
    //     localStorage.setItem("userDetail",this.email.map.email);
    //     localStorage.setItem("userRole","buyer");
        
    //   })
    // }) 
    
  }


  // addToCart(){
  //   const cart={
  //     buyerEmail:localStorage.getItem("userDetail"),
  //     productId: this.cartProduct.productId,
  //     productName:this.cartProduct.productName,
  //     sellingPrice:this.cartProduct.sellingPrice,
  //     productImage:this.cartProduct.productImage,
  //     productPrice:this.cartProduct.mrp,
  //     productQuantity:this.cartProduct.productQuantity,
  //     brand:this.cartProduct.brand,
  //     sellerEmail:this.cartProduct.sellerEmail
  //   }
  //   this.service.saveToCart(cart).subscribe(response=>{
  //     console.log(response);
  //     this.router.navigateByUrl('/mycart')
      
  //   })
  // }


  
}
