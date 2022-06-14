import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LocationModelComponent } from '../location-model/location-model.component';
import { ShopsListService } from './shops-list.service';

@Component({
  selector: 'app-shops-list',
  templateUrl: './shops-list.component.html',
  styleUrls: ['./shops-list.component.css']
})
export class ShopsListComponent implements OnInit {

  imageList:any;
  p:number=1;
  searchText: any;
  name:any;

  //checkbox
  selectAll = false;
  shopFilter=[
    {name : "Shop Name", checked : false},
    {name : "Location", checked : true},
    {name : "Pincode", checked : false}
  ];
  updateCheck(){
    if(this.selectAll === true){
      this.shopFilter.map((x)=>{
        x.checked=true;
      });
    }else {
      this.shopFilter.map((x)=>{
        x.checked=false;
      });
    }
  }
  

  constructor(private shopService: ShopsListService,private router:Router,private modalService: NgbModal ) { 
  }



  ngOnInit(): void {
    this.shopService.getShopDetail().subscribe( response=>{
      this.imageList=response;
      for(var i=0;i<this.imageList.length;i++){
        this.imageList[i].image='data:image/jpeg;base64,' +this.imageList[i].shopImage;

      }
    })
  }

  routing(email:any){
    console.log("this is seller email of this shop"+email);
    localStorage.setItem("shopselleremail",email)
    localStorage.setItem("flag","shopProducts");
    this.router.navigateByUrl('/navbar/productlist')
    // this.shopService.shopIdDataWithUrl(shopId);
    // this.router.navigateByUrl('/productlist')
  }

  open(data: any) {
    this.shopService.modelLocationData(data);
    const modalRef = this.modalService.open(LocationModelComponent, {size: 'lg'});
  }

}
