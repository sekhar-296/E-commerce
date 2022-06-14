import { Component, OnInit } from '@angular/core';
import { OrdersummaryService } from '../services/ordersummary.service';

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.css']
})
export class OrderSummaryComponent implements OnInit {
  summary:any

  
  constructor(private orderSummary:OrdersummaryService) { 
    this.summary = orderSummary.getOrders();
  }

  ngOnInit(): void {
  }

}
