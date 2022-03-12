import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UserLoginComponent } from './user-login/user-login.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { InventoryComponent } from './inventory/inventory.component';
import { UserStoreComponent } from './user-store/user-store.component';
import { AdminStoreComponent } from './admin-store/admin-store.component';

const routes: Routes =  [
  { path: 'login', component: UserLoginComponent},
  { path: 'user-store', component: UserStoreComponent},
  { path: 'admin-store', component: AdminStoreComponent},
  { path: 'cart', component: ShoppingCartComponent},
  { path: 'inventory', component: InventoryComponent}
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
