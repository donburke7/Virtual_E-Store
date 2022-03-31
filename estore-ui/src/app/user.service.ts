/**
 * SWEN 261
 * Services the user class of the beans store.
 * 
 * Contributors: Isaac Post
 */

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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

  /**
   * Checks to see if a {@linkplain User user} already exists by sending a username to the backend
   * @param username the username of the {@link User user} to check for
   * @returns a boolean indicating if the {@link User user} exists
   */
  userExists(username: string): Observable<boolean> {
      /**
       * Checks to see if the information inputted by the user
       * exists in the user data.
       * 
       * FIX ME add backend. - > Fixed
       */
      const url = `${this.usersURL}/${username}`;
      return this.http.get<boolean>(url);
      //return USERS.some(user => (user.username === username));
  }

  /**
   * Creates a new {@linkplain User user} by sending a passed in string to the backend
   * @param username the username to use to create the new {@link User user} with
   * @returns the newly created {@link User user}
   */
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

  /**
   * Retrieves a {@linkplain User user} by sending a string to the backend
   * @param username the username to use to search for a {@link User user}
   * @returns the retrieved {@link User user}
   */
  getUser(username: string): Observable<User> {
    const url = `${this.usersURL}/${username}`;
    return this.http.get<User>(url);
  }
}
