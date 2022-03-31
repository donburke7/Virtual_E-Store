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
   * Retrieves the inventory from the backend
   * 
   * @returns a list of {@linkplain Product products} that represents the inventory
   * of this store
   */
  getProducts(): Observable<Product[]> {
    const url = `${this.productsURL}`
    return this.http.get<Product[]>(url, this.httpOptions);
  }
  
  /**
   * sends information of the {@linkplain Product product} to be deleted from the inventory to the backend
   * 
   * @param id the id that is associated with the to be deleted {@link Product product}
   * 
   * @returns The {@link Product product} that was deleted
   */
  deleteProduct(id: number): Observable<Product> {
    const url = `${this.productsURL}/${id}`;
    return this.http.delete<Product>(url, this.httpOptions);
  }

  /**
   * Sends a {@linkplain Product product} back to the backend in order to update it's details
   * 
   * @param product The {@link Product product} that is to be updated
   * 
   * @returns an observable that is created from observing the return state of the put request
   * 
   */
  updateProduct(product: Product): Observable<any> {
    const url = `${this.productsURL}`
    return this.http.put(url, product, this.httpOptions);
  }

  /**
   * Gets a {@linkplain Product product} based on an id from the backend
   * @param id the id of the requested {@link Product product}
   * @returns the {@link Product product} that was retrieved
   */
  getProduct(id: number): Observable<Product> {
    const url = `${this.productsURL}/${id}`;
    return this.http.get<Product>(url);
  }

  /**
   * Sends information on a newly created {@linkplain Product product} to the backend 
   * @param product the newly created {@link Product product}
   * @returns The {@link Product product} that was sucessfully added to the inventory
   */
  addProduct(product: Product): Observable<Product> {
    const url = `${this.productsURL}`
    return this.http.post<Product>(url, product, this.httpOptions);
  }

  /**
   * Sends a string to the backend
   * The backend should then process the string in order to return
   * an array of {@linkplain Product products} that matches with the string
   * 
   * @param name the search string to use to search {@link Product products} with
   * 
   * @returns an array of {@link Product products} that matches the search string
   */
  searchProducts(name: string): Observable<Product[]> {
    if (!name.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    const url = `${this.productsURL}/?name=${name}`;
    return this.http.get<Product[]>(url);
  }
}
