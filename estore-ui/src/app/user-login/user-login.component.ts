/**
 * SWEN 261
 * Huser-login.components.ts
 * 
 * Contributors: Isaac Post
 */

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterEvent, RouterLink, RouterModule, RouterState } from '@angular/router';
import { Location } from '@angular/common';
import { UserService } from '../user.service';
import { Observable } from 'rxjs';
import { User } from '../user';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location,
    public router: Router
  ) { }

  ngOnInit(): void {
  }

  goBack(): void {
    this.location.back();
  }

  createUser(username: string) {
      // FIX ME -- CONNECT TO BACKEND
  }

  login(): void
  {
    /**
     * Checks the information inputted by the user to direct them
     * to either admin-store or user-store, or displaying the
     * incorrect login message.
     */
    var username = (<HTMLInputElement>document.getElementById("username-box")).value;
    // Admin Login
    if (username == 'admin')
    {
      this.router.navigate(['admin-store']);
    }

    // User Login
    else if (this.userService.userExists(username)) {
        this.router.navigate(['user-store']);
    }

    // New User
    else if(this.userService.createUser(username)){
        this.router.navigate(['user-store']);
    }

  }
}
