/**
 * SWEN 261
 * Shopping-cart component that interacts with shopping_cart.component.html
 * 
 * Contributors: Isaac Post
 */

import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ShoppingCartService } from '../shopping-cart.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  cart: Product[] = [];

  constructor(private shoppingCartService: ShoppingCartService, private location: Location) { }

  ngOnInit(): void {
    this.getCart();
  }

  /**
   * 
   * This should be changed to getCart() when backend is finished
   */
  getCart(): void {
    this.cart = this.shoppingCartService.getCart();
  }

  deleteProduct(product: Product): void {
    // FIX ME WHEN BACK END IMPLEMENTED
    this.cart = this.cart.filter(p => p !== product);
  }

  backButton(): void {
    this.location.back();
  }
}
