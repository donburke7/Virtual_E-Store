/**
 * SWEN 261
 * inventory.component.ts
 * 
 * The component that showcases the inventory
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

  /**
   * Initialization of this comopnent
   */
  ngOnInit(): void {
    this.getInventory();
  }

  /**
   * Retrieves the inventory and saves it in order to be displayed
   */
  getInventory(): void {
    this.productService.getProducts()
      .subscribe(inventory => this.inventory = inventory);
  }


  /**
     * Gets called by a button from html to remove product
     * 
     * @param product The product to be deleted.
     */
  deleteProduct(product: Product): void {
    this.inventory = this.inventory.filter(p => p !== product);
    this.productService.deleteProduct(product.id).subscribe();
  }

  /**
     * Gets called by a button from html to add a new product
     * 
     * @param product The name of the new product.
    */
  addProduct(name: String): void {

    name = name.trim();
    if (!name) { return; }
    this.productService.addProduct({ name } as Product).subscribe(product => {
      this.inventory.push(product);
    });
  }
}
