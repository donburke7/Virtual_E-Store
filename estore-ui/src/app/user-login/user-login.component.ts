import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterEvent, RouterLink, RouterModule, RouterState } from '@angular/router';
import { Location } from '@angular/common';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    // UserService
    private location: Location
  ) { }

  ngOnInit(): void {
  }

  goBack(): void {
    this.location.back();
  }

  // FOR TESTING PURPOSES
  copyToLabel(): void {
    //Reference the TextBox.
    var txtName = document.getElementById("username-box");

    //Reference the Label.
    var lblName = document.getElementById("lblTest");

    //Copy the TextBox value to Label.
    lblName!.innerHTML = (<HTMLInputElement>document.getElementById("username-box")).value; 
  }

  login(): void {
    var username = (<HTMLInputElement>document.getElementById("username-box")).value; 
    var password = (<HTMLInputElement>document.getElementById("password-box")).value; 

    // FIX ME Fix going to next page
    if (this.userService.user_exists(username, password)) {
        var url: string = 'www.google.com';
        
        // Figure out way to go storefront
    }

    else {
        var url: string = 'www.youtube.com';
        this.location.go(url);
    }
  }
}
