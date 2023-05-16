import { Component, Inject, LOCALE_ID, OnInit } from '@angular/core';
import { OrderService } from '../service/order.service';
import { Order } from '../model/order';
import { MatDialog } from '@angular/material/dialog';
import { formatCurrency } from '@angular/common';
import { IdDialogComponent } from '../id-dialog/id-dialog.component';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements  OnInit {

  orders: Order[];
  message: string;
  order: Order;
  dialogRef: any;
  

  constructor(private orderService : OrderService,
     @Inject(LOCALE_ID) public locale: string, public dialog: MatDialog) { }

  ngOnInit(): void {
   // this.orderService.getOrders().subscribe(orders => this.orders = orders);
   this.getOrders();
  }

  getOrders() {
    this.orderService.getOrders()
      .subscribe(orders => {
        this.orders = orders;
      });
  }

  processOrder(orderId: string) {
    this.orderService.processOrder(orderId)
      .subscribe(() => {
        this.message = 'Order Processed Successfully';
        this.getOrders(); // refresh orders
      });
  }

  formatStringCurrency(snum: string): string{

    const num = parseFloat(snum);
    return  formatCurrency(num,this.locale,'$');

  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    const month = date.getMonth() + 1;
    const day = date.getDate();
    const year = date.getFullYear();
    return `${month}/${day}/${year}`;
  }

  openOrderItemDialog(orderId: string) {

    this.order = this.orders.find(e => e.orderId === orderId) as Order;
    this.dialogRef = this.dialog.open(IdDialogComponent,
      { data: this.order, height: '220px', width: '400px' });
  
  } 
}
