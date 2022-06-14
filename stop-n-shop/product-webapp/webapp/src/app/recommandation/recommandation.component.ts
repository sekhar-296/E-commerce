import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';
import { ServiceImplService } from '../services/service-impl.service';

@Component({
  selector: 'app-recommandation',
  templateUrl: './recommandation.component.html',
  styleUrls: ['./recommandation.component.css']
})
export class RecommandationComponent implements OnInit {
  discount: any;
  discountPercentage: any;
  total: any;
  totalItemsIncart: any;
  totalBillAmount: any;
  totalDiscountonMRP: any;
  cart: any;
  temp: any;
  category = new FormControl();
  selectedFood2: any;
   selected: any;
   toggle = true;
   status = "Enable";
   categoryOption:any="";

  constructor(private http:HttpClient,private service:ServiceService,private s:ServiceImplService,private sanitizer:DomSanitizer
    ,private route:Router) { }
  recommand:any;
  p:number=1;
  product:any;
  chipControl = new FormControl(new Set());

  ngOnInit(): void {
    this.getRecommendedData();
    
    this.getData(localStorage.getItem("productCategory"));
    
     
  }


  options = ["Fruit", "Vegetables", "Beverages","Bread/Bakery","Dairy","Baking Goods","Meat","Personal Care","Health Care","Household & Cleaning Supplies","Baby Item","Snacks","Frozen Foods","Pharmacy","Others"];

  
  get chips() {
     return this.chipControl.value; };
  toggleChip = (chip: any) => {
    const addChip = () => { this.chips.add(chip); }; 
    const removeChip = () => { this.chips.delete(chip); };

    this.chips.has(chip) ? removeChip() : addChip();
  }

  
  enableDisableRule() {
    this.toggle = !this.toggle;
    this.status = this.toggle ? "Enable" : "Disable";
  }




  
  getData(category:any){
    this.categoryOption = category;
    localStorage.setItem('productCategory',category);
    this.service.getRecommandation().subscribe(  
      
      (resp:any)=>{
        console.log("in productdetails"+resp);
       
         this.recommand = resp;

        for(var img=0;img<this.recommand.length;img++){
          this.recommand[img].productImage='data:image/jpeg;base64,'+this.recommand[img].productImage  
          this.discount=((this.recommand[img].mrp-this.recommand[img].sellingPrice)*100)/this.recommand[img].mrp; 
          this.recommand[img].discount=Math.floor(this.discount)
        }
        
          console.log("clicked");
          
      }

    )
  }

  showData(data:any){
    console.log(data.productId);
    localStorage.setItem('productId', data.productId);
    localStorage.setItem("productCategory",data.productCategory)
    this.route.navigateByUrl("/navbar/productDetails")

  }

  
  getRecommendedData(){
    this.service.getRecommandation().subscribe(
      (resp:any)=>{
        console.log("in productdetails"+resp);
        this.recommand = resp;

        for(var img=0;img<this.recommand.length;img++){
          this.recommand[img].productImage='data:image/jpeg;base64,'+this.recommand[img].productImage;
          
          this.discount=((this.recommand[img].mrp-this.recommand[img].sellingPrice)*100)/this.recommand[img].mrp; 
          this.discountPercentage=Math.floor(this.discount);
        }
      }
    )
  }
  

  
  getCity(){
    this.categoryOption = "city";
    this.service.getRecommandationByCity().subscribe(
      
      
      (resp:any)=>{
        console.log("in productdetails"+resp);
        this.recommand = resp;
        for(var img=0;img<this.recommand.length;img++){
          this.recommand[img].productImage='data:image/jpeg;base64,'+this.recommand[img].productImage
            
          this.discount=((this.recommand[img].mrp-this.recommand[img].sellingPrice)*100)/this.recommand[img].mrp; 
          this.discountPercentage=Math.floor(this.discount);
        }
        
          console.log("city");
          
      }
      
    )
  }
  
   getCityAndCategory(){
    this.categoryOption = "City And Category";
    this.service.getRecommandationByBoth().subscribe(
      
      
      (resp:any)=>{
        console.log("in productdetails"+resp);
        this.recommand = resp;
        for(var img=0;img<this.recommand.length;img++){
          this.recommand[img].productImage='data:image/jpeg;base64,'+this.recommand[img].productImage
          this.discount=((this.recommand[img].mrp-this.recommand[img].sellingPrice)*100)/this.recommand[img].mrp; 
          this.discountPercentage=Math.floor(this.discount);
        }
        
        
          
      }
      
    )

   }

  //  onFoodSelection2() {
  //   console.log(this.getData());
  // }



  

}
