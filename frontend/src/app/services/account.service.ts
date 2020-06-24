import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  user: User;
  constructor(public http: HttpClient) { }

  createAccount(user: User) {
    return this.http.post('http://localhost:8080' + '/account/register', user);
  }


}
