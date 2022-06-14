
import { Component, OnInit} from '@angular/core';
import { OrderhistoryService } from '../services/orderhistory.service';
import { MatDialog} from '@angular/material/dialog';
import { InvoiceFormComponent } from '../invoice-form/invoice-form.component';
import { ServiceService } from '../service.service';
import { Router } from '@angular/router';
declare var window:any;
declare var $:any;
export interface DialogData {
  animal: string;
  name: string;
}
@Component({
  selector: 'app-orderhistory',
  templateUrl:'./orderhistory.component.html',
  styleUrls: ['./orderhistory.component.css']
})
export class OrderhistoryComponent implements OnInit {
  name: any;
  orders:any;
  p:number=1
  orderList:any=[];
  pdfTable: any;
  buyerResponse:any;
  constructor(private orderhistoryservice: OrderhistoryService,public dialog: MatDialog,private service:ServiceService,private route:Router) {
  }

  getOrderData():void{
    this.orderhistoryservice.getOrderList().subscribe((response:any)=>{
      // console.log("orders");
       console.log(response);
      
      this.orderList=response
      this.service.getCurrentData(localStorage.getItem('userDetail')).subscribe((buyer)=>{
        this.buyerResponse=buyer.userName;
      })

    })
  }
  saveData(data:any){
    localStorage.setItem('productId', data.productId);
    localStorage.setItem('productCategory', data.productCategory);
  }
 
  openDialog(order:any): void {
    localStorage.setItem("orderId",order.orderId)
    const dialogRef = this.dialog.open(InvoiceFormComponent, {
      width: '50%',height:'100%',panelClass:'my-dialog',
      data: order,
    });}
  ngOnInit(): void {
    // this.orderList.splice(0,this.orderList.length)
    this.getOrderData();
    }
    trackOrder(data:any){
      localStorage.setItem("orderId",data.orderId);
      this.route.navigateByUrl('/navbar/routes');
    }
  }
function htmlToPdfmake(innerHTML: any) {
  throw new Error('Function not implemented.');
}

 
  





 
  

  



