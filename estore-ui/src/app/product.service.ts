import { Injectable } from '@angular/core';
import { Product } from './product';
import { PRODUCTS } from './mock-products';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

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
   * GETS products from mock-products
   * @returns An array of products
   */
  getProducts(): Product[] {
    // FIX ME WHEN BACK END RUNS
    return PRODUCTS;
  }
  
  deleteProduct(id: number): Observable<Product> {
    const url = `${this.productsURL}/${id}`;

    return this.http.delete<Product>(url, this.httpOptions);
  }

  updateProduct(product: Product): Observable<any> {
    return this.http.put(this.productsURL, product, this.httpOptions)
  }

  getProduct(id: number): Observable<Product> {
    const url = `${this.productsURL}/${id}`;
    return this.http.get<Product>(url);
  }

  addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.productsURL, product, this.httpOptions);
  }

}
