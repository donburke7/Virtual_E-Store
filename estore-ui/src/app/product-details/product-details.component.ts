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
  
  constructor(private productService: ProductService, 
    private route: ActivatedRoute, private location: Location) { 
  }

  ngOnInit(): void {
    this.getProduct();
  }

  getProduct(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.productService.getProduct(id)
      .subscribe(product => this.product = product)
  }

  backButton(): void {
    this.location.back();
  }

  saveButton(): void {
    if (this.product) {
      this.productService.updateProduct(this.product)
        .subscribe(() => this.backButton());
    }
  }
}
