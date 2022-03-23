/**
 * SWEN 261
 * user-store.component.ts
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

  ngOnInit(): void {
    this.getInventory();
    this.getUser();
  }

  getInventory(): void {
    this.productService.getProducts()
      .subscribe(inventory => this.inventory = inventory);
  }

  getUser(): User {
    const username = this.route.snapshot.paramMap.get('username')!;

    this.userService.getUser(username)
      .subscribe(user => this.user = user)
    
    return this.user!;
    
  }

}
