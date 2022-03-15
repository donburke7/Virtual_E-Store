/**
 * SWEN 261
 * Services the shopping-cart class of the Beans store.
 * 
 * Contributors: Isaac Post, Donald Burke
 */

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PRODUCTS } from './mock-products';
import { Product } from './product';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  private shoppingCartURL = 'http://localhost:8080/shoppingCart';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getCart(): Observable<Product[]> {
    const url = `${this.shoppingCartURL}`
    return this.http.get<Product[]>(url, this.httpOptions);
  }
 
  addToCart(product: Product): Observable<any> {
    const url = `${this.shoppingCartURL}`;
    return this.http.put(url, product)
  }

  deleteProduct(product: Product, customer: User): Observable<any> {
    const url = `${this.shoppingCartURL}/${customer}/${product.id}`;
    return this.http.delete(url)
  }
}
