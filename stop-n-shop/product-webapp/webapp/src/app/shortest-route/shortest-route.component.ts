import { Component, OnInit } from '@angular/core';
import { ShortestRouteService } from './shortest-route.service';

@Component({
  selector: 'app-shortest-route',
  templateUrl: './shortest-route.component.html',
  styleUrls: ['./shortest-route.component.css']
})
export class ShortestRouteComponent implements OnInit {

  constructor(public shortestRouteService:ShortestRouteService) { }

  orderId:any;
  ngOnInit(): void {
     
    this.orderId=localStorage.getItem("orderId");
     this.findSellersEmailsByOrderId(this.orderId);
  }

  userAddress:any;
  getUserDetails(email:any){
    this.shortestRouteService.getUserDetails(email).subscribe( response=>{
      this.userAddress=response.address.streetName+","+response.address.city+","+response.address.state+","+response.address.pin;
      this.shortestRouteService.getCoordinates(this.userAddress).subscribe( response=>{
        this.record=response;
       
        this.arr.push( (this.record.features[0].properties.lon))
        this.arr.push( (this.record.features[0].properties.lat))
        //for adding marker in map
        const last2 = this.arr.slice(-2);
        this.markers.push(last2);
        this.findDirections(this.arr);
        });
    });
    
   }


  sellersEmails:any;
  length:any;
  allEmails:any=[];
  allCoordinates:any=[];
  arr:any=[];
  address:any;
  record:any;
  markers:any=[];

  findSellersEmailsByOrderId(orderId:any):void{
    this.getUserDetails(localStorage.getItem("userDetail"));
    this.shortestRouteService.getByOrderId(this.orderId).subscribe(response=>{

      this.length=response.producList.length;
      for(let i=0;i<this.length;i++){
      this.sellersEmails=response.producList[i].sellerEmail;
      
            this.shortestRouteService.getAddressByEmailId(this.sellersEmails).subscribe(response=>{
              this.address= response.address.addressLine1+","+response.address.addressLine2+","+response.address.city+","+response.address.pincode;
                // console.log(this.address);
                this.shortestRouteService.getCoordinates(this.address).subscribe( response=>{
                  this.record=response;
                 
                  this.arr.push( (this.record.features[0].properties.lon))
                  this.arr.push( (this.record.features[0].properties.lat))
                  //for adding marker in map
                  const last2 = this.arr.slice(-2);
                  this.markers.push(last2);
          
                  this.findDirections(this.arr);
                  });
              })
    }
    });
    // console.log(this.arr);
   }

   latitude!: number;
   longitude!: number;
   latlng=[];
   dirctionRecord:any;
   findDirections(arr:any):void{
   this.shortestRouteService.getDirections(this.arr).subscribe( response=>{
   this.dirctionRecord=response.routes[0].geometry.coordinates;
   this.latlng=this.dirctionRecord;
  // console.log(this.latlng);
   });
  }



  


}
