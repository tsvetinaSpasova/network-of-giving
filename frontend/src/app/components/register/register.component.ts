import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Gender } from 'src/app/models/gender.enum';
import { Router } from '@angular/router';
import { AccountService } from 'src/app/services/account.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  gender: String;
  options: Gender;
  constructor(private router: Router, private accountService: AccountService) { }

  ngOnInit(): void {

  }

  btnSignup() {

  }

  btnCancel() {
    this.router.navigate(['/home']);
}
}
