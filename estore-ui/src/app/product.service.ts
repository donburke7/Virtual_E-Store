/**
 * SWEN 261
 * Services the product class of the Beans store.
 * 
 * Contributors: Isaac Post, Donald Burke
 */

import { Injectable } from '@angular/core';
import { Product } from './product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private productsURL = 'http://localhost:8080/products';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  /**
   * 
   * @returns 
   */
  getProducts(): Observable<Product[]> {
    const url = `${this.productsURL}`
    return this.http.get<Product[]>(url, this.httpOptions);
  }
  
  /**
   * 
   * @param id 
   * @returns 
   */
  deleteProduct(id: number): Observable<Product> {
    const url = `${this.productsURL}/${id}`;
    return this.http.delete<Product>(url, this.httpOptions);
  }

  /**
   * 
   * @param product 
   * @returns 
   */
  updateProduct(product: Product): Observable<any> {
    const url = `${this.productsURL}`
    return this.http.put(url, product, this.httpOptions);
  }

  getProduct(id: number): Observable<Product> {
    const url = `${this.productsURL}/${id}`;
    return this.http.get<Product>(url);
  }

  addProduct(product: Product): Observable<Product> {
    const url = `${this.productsURL}`
    return this.http.post<Product>(url, product, this.httpOptions);
  }

  searchProducts(name: string): Observable<Product[]> {
    const url = `${this.productsURL}/${name}`;
    return this.http.get<Product[]>(url);
  }
}
