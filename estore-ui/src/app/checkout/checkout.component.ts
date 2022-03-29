import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../product';
import { ShoppingCartService } from '../shopping-cart.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  @Input() cart?: Product[];

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private shoppingCartSerive: ShoppingCartService
  ) { }

  ngOnInit(): void
  {
    this.getCart();
  }

  getCart(): void
  {
    var username = (this.route.snapshot.paramMap.get('username')!);
    this.shoppingCartSerive.getCart(username).subscribe(cart => this.cart = cart);
  }

  checkout(): void
  {
    //this.shoppingCartSerive.checkout(username);
  }

  backButton(): void
  {
    //this.location.back();
  }

}
