import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { USERS } from './mock-users';
import { User } from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private productsURL = 'https://localhost:8080/users';

  constructor(private http: HttpClient) { }

  //FIX ME Not functioning
  user_exists(username: string, password: string): boolean {
      const user: User = {username: username, password: password};

      if (USERS.indexOf(user) > -1) {
          return true;
      }
    
      else { 
        return false;
      }
  }
}
