/**
 * SWEN 261
 * product-details.component.ts
 * 
 * The component that shows the details of a {@link Product product} to the admin
 * This is where an admin is able to change the details of said {@link Product product}
 * 
 * Contributors: Isaac Post, Donald Burke
 */

import { Component, Input, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Product } from '../product';
import { ActivatedRoute } from '@angular/router';
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
      private location: Location) { 
  }

  /**
   * on initialization of this component
   */
  ngOnInit(): void {
    this.getProduct();
  }

  /**
   * Gets a specific {@linkplain Product product} to display
   */
  getProduct(): void {
    /**
     * Gets the id from the route to get the product
     */
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);

    this.productService.getProduct(id)
      .subscribe(product => this.product = product)
  }

  /**
   * Returns the user to the previous page
   */
  backButton(): void {
    this.location.back();
  }

  /**
   * allows the admin to save the {@linkplain Product product} that they have just modified
   */
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
