import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AddProductServiceService } from '../services/add-product-service.service';

@Component({
  selector: 'app-add-products',
  templateUrl: './add-products.component.html',
  styleUrls: ['./add-products.component.css']
})

export class AddProductsComponent implements OnInit {
  
  active:number=0;
  flag:boolean=false;
  product:any;
  productTosend:any;
  selectedFile:any;
  url:any='';
  allProducts:any=[];
  productsList:any=[];
  sellerEmail:any;

  constructor(private addproductservice:AddProductServiceService) { }

  ngOnInit(): void {
    this.sellerEmail=localStorage.getItem("userDetail");
    console.log("email");
    
    console.log(this.sellerEmail);
    
  }


  categoryBrand=new FormGroup({
    productCategory:new FormControl('',[Validators.required]),
    brand:new FormControl('',[Validators.required]),
    file:new FormControl('',[Validators.required])
  })


  get productCategory(){
    return this.categoryBrand.controls['productCategory']
  }
  get brand(){
    return this.categoryBrand.controls['brand']
  }
  get file(){
    return this.categoryBrand.controls['file']
  }


  productInfoForm=new FormGroup({
    productName:new FormControl('',[Validators.required]),
    mrp:new FormControl('',[Validators.required]),
    sellingPrice:new FormControl('',[Validators.required]),
    productQuantity:new FormControl('',[Validators.required]),
    description:new FormControl('',[Validators.required])
  })


  get productName(){
    return this.productInfoForm.controls['productName']
  }
  get mrp(){
    return this.productInfoForm.controls['mrp']
  }
  get sellingPrice(){
    return this.productInfoForm.controls['sellingPrice']
  }
  get productQuanitiy(){
    return this.productInfoForm.controls['productQuantity']
  }
  get description(){
    return this.productInfoForm.controls['description']
  }

  myFile(data:any){
    this.selectedFile=data.target.files[0];
    var reader=new FileReader();
    reader.readAsDataURL(this.selectedFile)
    reader.onload=(data:any)=>{
      this.url=data.target.result
    }
  }


  myProductInfoFunction(){
    this.active=2;
     this.product={
        productName:this.productInfoForm.value.productName,
        mrp:this.productInfoForm.value.mrp,
        sellingPrice:this.productInfoForm.value.sellingPrice,
        productQuantity:this.productInfoForm.value.productQuantity,
        description:this.productInfoForm.value.description,
        productCategory:this.categoryBrand.value.productCategory,
        brand:this.categoryBrand.value.brand,
        file:this.url
      }
      this.allProducts.push(this.product);

      this.productTosend={
        productName:this.productInfoForm.value.productName,
        mrp:this.productInfoForm.value.mrp,
        sellingPrice:this.productInfoForm.value.sellingPrice,
        productQuantity:this.productInfoForm.value.productQuantity,
        description:this.productInfoForm.value.description,
        productCategory:this.categoryBrand.value.productCategory,
        brand:this.categoryBrand.value.brand,
        file:this.selectedFile,
        sellerEmail:this.sellerEmail
      }
      console.log("proudct to send"+this.productTosend.productName);
      
      this.productsList.push(this.productTosend);
    
  }


  deleteFunction(index:any){
    this.allProducts.splice(index,1);
    this.productsList.splice(index,1);
  }


  AddProducts(){
    for(var i=0;i<this.productsList.length;i++){
      var image=new FormData();
      var data;
      data=image.append('file',this.productsList[i].file,this.productsList[i].file.name);
      delete this.productsList[i]['file'];
      image.append('Product',JSON.stringify(this.productsList[i]));
      this.addproductservice.addProducts(image).subscribe(a=>{
        console.log(a);
      })

    }
  }
 Next(){
   if(this.active<2){
  this.active=this.active+1;
   }
 }
 Previous(){
   this.active=this.active-1;
 }
 addMore(){
   this.active=0;
   this.productInfoForm.reset();
   this.categoryBrand.reset();
 }

}
