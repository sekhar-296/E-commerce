import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { DealOfTheDayService } from '../services/deal-of-the-day.service';
import { ProductlistserviceService } from '../services/productlistservice.service';

@Component({
  selector: 'app-deal-of-the-day',
  templateUrl: './deal-of-the-day.component.html',
  styleUrls: ['./deal-of-the-day.component.css'],
  animations:[
    trigger('fade',[

      state('void',style({opacity:0})),

      // transition('void => *,* => void ',[
      //   style({ backgroundcolor:'red',opacity:0}),
      //   animate(1000)
      // ]),

      transition('void <=> *',[
        animate(1000)
      ])

     
    ])
  ]
})
export class DealOfTheDayComponent implements OnInit {

productList:any=[];
p:number=1
productData:any=[];
product:any;
discount:any;
discountPercentage:any;
temp:any;

  constructor(private dealoftheday:DealOfTheDayService, private productlistserviceService:ProductlistserviceService  ) {
    
   }

  ngOnInit(): void {

    this.productlistserviceService.getProductList().subscribe((response:any)=>{
     // console.log(response);
      response.forEach((element:any)=> {
      
        this.productList.push(element)
         
      for(var i=0;i<this.productList.length;i++){

        this.productList[i].image='data:image/jpeg;base64,' +this.productList[i].productImage; 

        this.discount=(((this.productList[i].mrp)-(this.productList[i].sellingPrice))*100)/this.productList[i].mrp; 
        this.productList[i].discountPercentage=Math.floor(this.discount);     
          

      }
      
      this.productData=this.productList.filter( (x:any)=> {
        return x.discountPercentage >= 40
      })
 

      });
    })


  }



  saveData(data:any){
    localStorage.setItem('productId', data.productId);
    localStorage.setItem('productCategory', data.productCategory);
    
  }

  productId:any;
  myFunction(productId:any){
    this.productId=this.productlistserviceService.productDetail;
  }
 
}
