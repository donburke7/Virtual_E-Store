/***
 * SWEN 261
 * user-product-view.components.ts
 * 
 * Contributors: Isaac Post
 */

import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { Location } from '@angular/common';
import { ShoppingCartService } from '../shopping-cart.service';

@Component({
  selector: 'app-user-product-view',
  templateUrl: './user-product-view.component.html',
  styleUrls: ['./user-product-view.component.css']
})
export class UserProductViewComponent implements OnInit {
  @Input() product?: Product;

  constructor(
      private productService: ProductService,
      private shoppingCartService: ShoppingCartService,
      private route: ActivatedRoute,
      private location: Location,
  ) { }

  ngOnInit(): void {
    this.getProduct();
  }

  getProduct(): void {
    /**
     * Gets the id of the current product page
     */
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);

    this.productService.getProduct(id)
      .subscribe(product => this.product = product)
  }

  backButton(): void {
    this.location.back();
  }

  addToCart(product: Product): void {
    /**
     * Takes in a product to add to the user's cart
     */
    var username = (this.route.snapshot.paramMap.get('username')!);
    this.shoppingCartService.addToCart( product, username ).subscribe(product => {
      product.push(product);
    });
  }

  addRate(rating: string): void {
    /**
     * Adds a rating from a user
     */
    if (this.product) {
      this.product.ratings.push(parseInt(rating));
      this.productService.updateProduct(this.product).subscribe(() => this.product);
    }
  }
}
