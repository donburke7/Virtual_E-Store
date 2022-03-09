import { Injectable } from '@angular/core';
import { Product } from './product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private productsURL = 'https://localhost:8080/inventory';

  constructor(private http: HttpClient) { }

  /**
   * GETS products from Server
   * @returns An Observable of a Product array
   */
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productsURL);
  }
  
}
