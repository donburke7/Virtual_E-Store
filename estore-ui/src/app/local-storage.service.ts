/**
 * Saves the username of the currently logged in {@linkplain User user}
 * In order to be retrieved from other components
 * 
 * @author Team jadin
 */

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService
{
  
  private username!: string;

  constructor() { }

  /**
   * Sets the username
   * @param username the username to save
   */
  setUsername(username: string): void
  {
    this.username = username;
  }

  /**
   * Retrieves the username of the currently logged in {@linkplain User user}
   * @returns the username of the currently logged in {@link User user}
   */
  getUsername(): string
  {
    return this.username;
  }

}
