import { Component, OnInit } from '@angular/core';
import { OrderhistoryService } from '../services/orderhistory.service';
import jspdf from 'jspdf';
import html2canvas from 'html2canvas';
import { MatDialogRef } from '@angular/material/dialog';
import { ServiceService } from '../service.service';
@Component({
  selector: 'app-invoice-form',
  templateUrl: './invoice-form.component.html',
  styleUrls: ['./invoice-form.component.css']
})
export class InvoiceFormComponent implements OnInit {
 
  response:any;
  buyerResponse:any;
  constructor(private orderhistoryservice: OrderhistoryService,public dialogRef: MatDialogRef<InvoiceFormComponent>,
    private service:ServiceService) { }

  captureScreen():void {
    var data = document.getElementById('pdfTable')!;
    
    html2canvas(data).then(canvas => {
      // Few necessary setting options
      var imgWidth = 208;
      var pageHeight = 295;
      var imgHeight = canvas.height * imgWidth / canvas.width;
      var heightLeft = imgHeight;
      const contentDataURL = canvas.toDataURL('image/png')
      let pdf = new jspdf('p', 'mm', 'a4'); // A4 size page of PDF
      var position = 0;
      pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight)
      pdf.save('MYPdf.pdf'); // Generated PDF
    });
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
    this.orderhistoryservice.getOrderById(localStorage.getItem("orderId")).subscribe(a=>{
      this.response=a;
    })
    this.service.getCurrentData(localStorage.getItem('userDetail')).subscribe((buyer)=>{
      this.buyerResponse=buyer.userName;
    })
  }
}