/**
 * SWEN 261
 * product-search.component.ts
 * 
 * Contributors: Isaac Post, Donald Burke
 */

import { Component, Input, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { LocalStorageService } from '../local-storage.service';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { User } from '../user';

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {
  products$!: Observable<Product[]>
  private searchTerms = new Subject<string>();
  @Input() username!: string;

  constructor(private productService: ProductService, private localStorage: LocalStorageService) { }

  search(term: string): void {
      this.searchTerms.next(term);
  }

  ngOnInit(): void
  {
    this.username = this.localStorage.getUsername();
    this.products$ = this.searchTerms.pipe(
        // wait 300ms after each keystroke before considering the term
        debounceTime(300),
  
        // ignore new term if same as previous term
        distinctUntilChanged(),
  
        // switch to new search observable each time the term changes
        switchMap((term: string) => this.productService.searchProducts(term)),
    );
  }
}
