import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(public http: HttpClient) { }

  createAccount(user: User) {
    return this.http.post( 'http://localhost:8080/account/register', user);
  }
}
