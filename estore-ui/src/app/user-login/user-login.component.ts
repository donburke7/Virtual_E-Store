/**
 * SWEN 261
 * Huser-login.components.ts
 * 
 * This component handles the action of logging in for the {@linkplain User user}
 * 
 * Contributors: Isaac Post
 */

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { UserService } from '../user.service';
import { Observable } from 'rxjs';
import { User } from '../user';
import { LocalStorageService } from '../local-storage.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit
{

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location,
    public router: Router,
    private localStorage: LocalStorageService
  ) { }

  /**
   * The initialization of this component
   */
  ngOnInit(): void
  {
  }

  /**
   * Returns the current {@linkplain User user} to the previous page
   */
  goBack(): void
  {
    this.location.back();
  }

  /**
   * The action of logging in
   * This methods gets the input in the login textbox and uses that to determine whether or not to proceed
   * To the admin page or the customer's page
   */
  login(): void
  {
    /**
     * Checks the information inputted by the user to direct them
     * to either admin-store or user-store, or displaying the
     * incorrect login message.
     */
    var username = (<HTMLInputElement>document.getElementById("username-box")).value;
    this.localStorage.setUsername(username);
    // Admin Login
    if (username == 'admin')
    {
      this.router.navigate(['admin-store']);
    }

    // User Login
    else if (this.userService.userExists(username))
    {
      this.router.navigate([`user-store/${username}`]);
    }

    // New User
    else
    {
      this.userService.createUser(username);
      this.router.navigate([`user-store/${username}`]);
    }

  }
}
