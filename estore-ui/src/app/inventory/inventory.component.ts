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
    // FIX ME WHEN BACK END IMPLEMENTED
    this.inventory = this.inventory.filter(p => p !== product);
    this.productService.deleteProduct(product.id).subscribe();
  }

  addProduct(name: String): void {
    name = name.trim();
    if (!name) { return; }
    this.productService.addProduct({ name } as Product).subscribe(product => {
      this.inventory.push(product);
    });
  }
}
