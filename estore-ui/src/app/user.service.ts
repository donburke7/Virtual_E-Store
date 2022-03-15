/**
 * SWEN 261
 * Services the user class of the beans store
 * 
 * Contributors: Isaac Post
 */

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { USERS } from './mock-users';
import { User } from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private usersURL = 'https://localhost:8080/users';

  constructor(private http: HttpClient) { }

  user_exists(username: string, password: string): boolean {
      /**
       * Checks to see if the information inputted by the user
       * exists in the user data.
       * 
       * FIX ME add backend
       */
      const user: User = {username: username, password: password};

      return USERS.some(user => (user.password === password && user.username === username));
  }
}
