/**
 * SWEN 261
 * 
 * Holds functions relating to the user-login component.
 */

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
    private location: Location,
    public router: Router
  ) { }

  ngOnInit(): void {
  }

  goBack(): void {
    this.location.back();
  }

  incorrectLogin(): void {
    /**
     * Sets the badLogin label to the error message if the
     * username and password inputted is incorrect.
     */
    var txtName = "User not found.";

    //Reference the Label.
    var lblName = document.getElementById("badLogin");

    //Copy the TextBox value to Label.
    lblName!.innerHTML = txtName; 
  }

  login(): void {
    /**
     * Checks the information inputted by the user to direct them
     * to either admin-store or user-store, or displaying the
     * incorrect login message.
     */
    var username = (<HTMLInputElement>document.getElementById("username-box")).value; 
    var password = (<HTMLInputElement>document.getElementById("password-box")).value; 

    // Admin Login
    if (username == 'admin') {
        this.router.navigate(['admin-store']);
    }

    // User Login
    else if (this.userService.user_exists(username, password)) {
        this.router.navigate(['user-store']);
    }

    // Bad Info Login
    else {
        this.incorrectLogin();
    }
  }
}
