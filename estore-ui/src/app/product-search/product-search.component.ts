import { Component, NgIterable, OnInit } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {
  products$!: Observable<Product>
  private searchTerms = new Subject<string>();

  constructor(private productService: ProductService) { }

  search(term: string): void {
      this.searchTerms.next(term);
  }

  ngOnInit(): void {
  }
}
