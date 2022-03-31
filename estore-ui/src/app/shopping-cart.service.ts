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
  
  /**
   * Retrieves the shopping cart of the customer from the backend
   * 
   * @param username the username of the {@link User user} 
   * 
   * @returns an array of {@link Product products} that are in the shopping cart
   */
  getCart(username: string): Observable<Product[]> {
    const url = `${this.shoppingCartURL}/${username}`
    return this.http.get<Product[]>(url);
  }
 
  /**
   * Adds a {@linkplain Product product} to the customer's shopping cart
   * 
   * @param product the {@link Product product} to be added
   * @param amount the amount to set the added {@link Product product} to
   * @param username the username of the {@link User user} that initiated the action
   * 
   * @returns an observable that is created from observing the return state of the put request
   */
  addToCart(product: Product, amount: number, username: string): Observable<any> {
    const url = `${this.shoppingCartURL}/${username}/${amount}/${product.id}`;
    return this.http.put(url, this.http);
  }

  /**
   * deletes a {@linkplain Product product} from the customer's shopping cart
   * 
   * @param product the {@link Product product} that is to be deleted
   * @param username the username associated with the customer
   * 
   * @returns an observable that is created from observing the return state of the delete request
   */
  deleteProduct(product: Product, username: string): Observable<any> {
    const url = `${this.shoppingCartURL}/${username}/${product.id}`;
    return this.http.delete(url)
  }

  /**
   * Initiates the checkout action for the customer by sending
   * the requested customer's username back to the backend 
   * 
   * @param username the username of the customer
   * 
   * @returns an observable that is created from observing the return state of the post request
   */
  checkout(username: string): Observable<any>
  {
    const url = `${this.shoppingCartURL}/${username}`;
    return this.http.post(url, this.http);
  }
}
