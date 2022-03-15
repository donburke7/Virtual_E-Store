/**
 * SWEN 261
 * user-store.component.ts
 * 
 * Contributors: Isaac Post, Donald Burke
 */

import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-user-store',
  templateUrl: './user-store.component.html',
  styleUrls: ['./user-store.component.css']
})
export class UserStoreComponent implements OnInit {
  inventory: Product[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getInventory();
  }

  getInventory(): void {
    this.inventory = this.productService.getProducts();
  }
}
