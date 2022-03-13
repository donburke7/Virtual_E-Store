import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UserLoginComponent } from './user-login/user-login.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { InventoryComponent } from './inventory/inventory.component';
import { UserStoreComponent } from './user-store/user-store.component';
import { ProductDetailsComponent } from './product-details/product-details.component';

const routes: Routes =  [
  { path: 'login', component: UserLoginComponent},
  { path: 'user-store', component: UserStoreComponent},
  { path: 'cart', component: ShoppingCartComponent},
  { path: 'admin-store', component: InventoryComponent},
  { path: 'product-details', component: ProductDetailsComponent }
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
