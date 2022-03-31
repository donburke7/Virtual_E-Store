/**
 * SWEN 261
 * product-search.component.ts
 * 
 * This is the component that handles the {@linkplain User users} searching for a {@link Product product}
 * 
 * Contributors: Isaac Post
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

  /**
   * Method that handles input from the serach bar
   * This updates the string saved in this component to constantly update the results
   * Whenever a {@linkplain User user} inputs another character in the serach box
   * 
   * @param term The string that is currently being searched for
   */
  search(term: string): void {
      this.searchTerms.next(term);
  }

  /**
   * Upon initialization this method gets the {@linkplain Product products} that matches this component's search string
   */
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
