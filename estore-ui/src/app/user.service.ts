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
  private usersURL = 'http://localhost:8080/user';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  userExists(username: string): Observable<User> {
      /**
       * Checks to see if the information inputted by the user
       * exists in the user data.
       * 
       * FIX ME add backend. - > Fixed
       */
    
    const url = `${this.usersURL}/${username}`;
    return this.http.get<User>(url, this.httpOptions);
      
  }

  createUser(username: string): Observable<User> {
      /**
       * Creates a new user by passing on the username
       * 
       * Input Arguments:
       * username -- The username of the new user
       */
    const url = `${this.usersURL}/${username}`;
    return this.http.post<User>(url, this.httpOptions);
  }
}
