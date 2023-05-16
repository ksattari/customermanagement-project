import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, map, tap }  from 'rxjs/operators';
import { Order } from '../model/order';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseUrl = 'http://localhost:7073';

  constructor(private http: HttpClient, private snackBar: MatSnackBar) { }

  getOrders(): Observable<Order[]> {
    const url = `${this.baseUrl}/getOrders`;

    return this.http.get<Order[]>(url).pipe(
      map(orders => {
        return orders.map(order => {
        order.orderDate = new Date(order.orderDate);
        return order;
        });
      })
    )
  }

  public processOrder(num: string): Observable<any> {
    const url = `${this.baseUrl}/deleteOrder/${num}`;
    return this.http.delete(url).pipe(
      tap(() => {
        this.snackBar.open('Order processed', 'Close', { duration: 3000 });
      }),
      catchError((error) => {
        this.snackBar.open('Error processing order', 'Close', { duration: 3000 });
        throw error;
      })
    );
  }
}
