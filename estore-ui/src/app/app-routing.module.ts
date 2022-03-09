import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UserLoginComponent } from './user-login/user-login.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';

const routes: Routes =  [
  { path: 'login', component: UserLoginComponent},
  { path: 'cart', component: ShoppingCartComponent},
  
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
