/**
 * SWEN 261
 * product-details.component.ts
 * 
 * Contributors: Isaac Post, Donald Burke
 */

import { Component, Input, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Product } from '../product';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  @Input() product?: Product;
  
  constructor(
      private productService: ProductService, 
      private route: ActivatedRoute, 
      public router: Router,
      private location: Location) { 
  }

  ngOnInit(): void {
    this.getProduct();
  }

  getProduct(): void {
    /**
     * Gets the id from the route to get the product
     */
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);

    this.productService.getProduct(id)
      .subscribe(product => this.product = product)
  }

  backButton(): void {
    this.location.back();
  }

  saveButton(): void {
    /**
     * Saves the current form of the product
     */
    if (this.product) {
      this.productService.updateProduct(this.product)
        .subscribe(() => this.backButton());
    }
  }
  
}
