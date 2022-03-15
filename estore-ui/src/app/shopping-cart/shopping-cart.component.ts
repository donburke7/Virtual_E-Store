/**
 * SWEN 261
 * shopping-cart.component.ts
 * 
 * Contributors: Isaac Post
 */

import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ShoppingCartService } from '../shopping-cart.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  cart: Product[] = [];

  constructor(
      private shoppingCartService: ShoppingCartService, 
      private location: Location,
      private route: ActivatedRoute
      ) { }

  ngOnInit(): void {
    this.getCart();
  }

  getCart(): void {
    /**
     * Gets the id from the route to get the cart
     */

    // FIX ME
    this.shoppingCartService.getCart()
      .subscribe(cart => this.cart = cart);
  }

  deleteProduct(product: Product): void {
    /**
     * Initilized with a button and deletes the product from the cart
     * 
     * Input Argument:
     * product -- The product to be deleted
     */

    var username = (this.route.snapshot.paramMap.get('username')!);
    this.cart = this.cart.filter(p => p !== product);
    this.shoppingCartService.deleteProduct(product, {"username": username}).subscribe();
  }

  backButton(): void {
    this.location.back();
  }
}
