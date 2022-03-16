/**
 * SWEN 261
 * inventory.component.ts
 * 
 * Contributors: Donald Burke
 */

import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {
  inventory: Product[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getInventory();
  }

  getInventory(): void {
    this.productService.getProducts()
      .subscribe(inventory => this.inventory = inventory);
  }

  deleteProduct(product: Product): void {
    /**
     * Gets called by a button from html to remove product
     * 
     * Input Arguments:
     * product -- The product to be deleted.
     */

    this.inventory = this.inventory.filter(p => p !== product);
    this.productService.deleteProduct(product.id).subscribe();
  }

  addProduct(name: String): void {
    /**
     * Gets called by a button from html to add a new product
     * 
     * Input Arguments:
     * product -- The name of the new product.
    */

    name = name.trim();
    if (!name) { return; }
    this.productService.addProduct({ name } as Product).subscribe(product => {
      this.inventory.push(product);
    });
  }
}
