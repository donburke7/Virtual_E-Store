/**
 * SWEN 261
 * Services the shopping-cart class of the Beans store
 * 
 * Contributors: Isaac Post, Donald Burke
 */

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PRODUCTS } from './mock-products';
import { Product } from './product';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getCart(): Product[] {
    // FIX ME WHEN BACK END RUNS
    return PRODUCTS;
  }
}
