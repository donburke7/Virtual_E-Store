/**
 * SWEN 261
 * Services the product class of the Beans store
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
  private productsURL = 'https://localhost:8080/inventory';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  /**
   * GETS products from http client
   * @returns An array of products
   */
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productsURL);
  }
  
  deleteProduct(id: number): Observable<Product> {
    const url = `${this.productsURL}/${id}`;

    return this.http.delete<Product>(url, this.httpOptions);
  }

  updateProduct(product: Product): Observable<any> {
    return this.http.put(this.productsURL, product, this.httpOptions);
  }

  getProduct(id: number): Observable<Product> {
    const url = `${this.productsURL}/${id}`;
    return this.http.get<Product>(url);
  }

  addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.productsURL, product, this.httpOptions);
  }

  searchProducts(name: string): Observable<Product[]> {
    if (!name.trim()) {
      // if not search term, return empty product array.
      return of([]);
    }

    const url = `${this.productsURL}/${name}`;
    return this.http.get<Product[]>(url);

  }
}
