import { Injectable } from '@angular/core';
import { Product } from './product';
import { PRODUCTS } from './mock-products';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private productsURL = 'https://localhost:8080/inventory';

  constructor(private http: HttpClient) { }

  /**
   * GETS products from mock-products
   * @returns An array of products
   */
  getProducts(): Product[] {
    return PRODUCTS;
  }
  
  deleteProduct(product: Product) {
    
  }

}
