/**
 * SWEN 261
 * Declares the components of the Beans store.
 * 
 * Contributors: Isaac Post, Donald Burke
 */

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserStoreComponent } from './user-store/user-store.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { InventoryComponent } from './inventory/inventory.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { ProductSearchComponent } from './product-search/product-search.component';
import { UserProductViewComponent } from './user-product-view/user-product-view.component';

@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    InventoryComponent,
    ShoppingCartComponent,
    UserStoreComponent,
    ProductDetailsComponent,
    ProductSearchComponent,
    UserProductViewComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
