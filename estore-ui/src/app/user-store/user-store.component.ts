/**
 * SWEN 261
 * user-store.component.ts
 * 
 * This component showcases a customer's store front page
 * On this page the customer is able to serach for a product,
 * See the entire inventory of said store, as well as access their shopping cart
 * 
 * Contributors: Isaac Post, Donald Burke
 */

import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-store',
  templateUrl: './user-store.component.html',
  styleUrls: ['./user-store.component.css']
})
export class UserStoreComponent implements OnInit {
  inventory: Product[] = [];
  @Input() user?: User;

  constructor(
      private productService: ProductService,
      private userService: UserService,
      private route: ActivatedRoute) { }

  /**
   * Initialization of this component
   */
  ngOnInit(): void {
    this.getInventory();
    this.getUser();
  }

  /**
   * Gets and saves the entire inventory of the store
   * in order to display the {@linkplain Product products} within said inventory
   * for the customers to see
   */
  getInventory(): void {
    this.productService.getProducts()
      .subscribe(inventory => this.inventory = inventory);
  }

  /**
   * Gets the {@linkplain User user} that is currently logged in 
   * @returns The {@link User user} that is logged in
   */
  getUser(): User {
    const username = this.route.snapshot.paramMap.get('username')!;

    this.userService.getUser(username)
      .subscribe(user => this.user = user)
    
    return this.user!;
    
  }

}
