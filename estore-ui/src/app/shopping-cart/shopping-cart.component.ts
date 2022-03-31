/**
 * SWEN 261
 * shopping-cart.component.ts
 * 
 * Contributors: Isaac Post, Donald Burke
 */

import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../product';
import { ShoppingCartService } from '../shopping-cart.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  @Input() username!: string;
  cart: Product[] = [];
  @Input() user?: User;
  
  constructor(
      private shoppingCartService: ShoppingCartService, 
      private userService: UserService,
      private location: Location,
      private route: ActivatedRoute
      ) { }

  ngOnInit(): void
  {
    this.getUser();
    this.getCart();
  }

  getCart(): void {
    /**
     * Gets the id from the route to get the cart
     */
    this.shoppingCartService.getCart(this.username)
      .subscribe(cart => this.cart = cart);
  }

  getUser(): void
  {
    this.username = this.route.snapshot.paramMap.get('username') as string;
    this.userService.getUser(this.username)
      .subscribe(user => this.user = user);
  }

  deleteProduct(product: Product): void {
    /**
     * Initilized with a button and deletes the product from the cart
     * 
     * Input Argument:
     * product -- The product to be deleted
     */

    this.cart = this.cart.filter(p => p !== product);
    this.shoppingCartService.deleteProduct(product, this.username).subscribe();
  }

  backButton(): void {
    this.location.back();
  }

  checkout(): void
  {
    this.shoppingCartService.checkout(this.username);
  }

}
