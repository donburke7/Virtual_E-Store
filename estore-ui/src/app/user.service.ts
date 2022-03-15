/**
 * SWEN 261
 * Services the user class of the beans store.
 * 
 * Contributors: Isaac Post
 */

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { USERS } from './mock-users';
import { User } from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private usersURL = 'https://localhost:8080/users';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getUser(username: string): Observable<User> {
      /**
       * Checks to see if the information inputted by the user
       * exists in the user data.
       * 
       * FIX ME add backend. - > Fixed
       */
      const user: User = {username: username};

      const url = `${this.usersURL}/${username}`;
      return this.http.get<User>(url);
      //return USERS.some(user => (user.username === username));
  }

  createUser(username: string): void {
      /**
       * Creates a new user by passing on the username
       * 
       * Input Arguments:
       * username -- The username of the new user
       */
  }
}
