/**
 * SWEN 261
 * Services the shopping-cart class of the Beans store.
 * 
 * Contributors: Isaac Post, Donald Burke
 */

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from './product';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  private shoppingCartURL = 'http://localhost:8080/shoppingCart';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getCart(username: string): Observable<Product[]> {
    const url = `${this.shoppingCartURL}/${username}`
    return this.http.get<Product[]>(url);
  }
 
  addToCart(product: Product, amount: number, username: string): Observable<any> {
    const url = `${this.shoppingCartURL}/${username}/${amount}/${product.id}`;
    return this.http.put(url, this.http);
  }

  deleteProduct(product: Product, username: string): Observable<any> {
    const url = `${this.shoppingCartURL}/${username}/${product.id}`;
    return this.http.delete(url)
  }

  checkout(username: string): Observable<any>
  {
    const url = `${this.shoppingCartURL}/${username}`;
    return this.http.post(url, this.http);
  }
}
