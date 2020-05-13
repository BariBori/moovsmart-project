import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Credentials } from 'src/app/models/Credentials';
import { UserService } from 'src/app/services/user.service';
import {browser} from 'protractor';
import {nextMonthDisabled} from '@ng-bootstrap/ng-bootstrap/datepicker/datepicker-tools';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {


  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [''],
      password: [''],
    });
  }

  onSubmit(): void {
    const credentials = this.loginForm.value as Credentials;
    this.userService.authenticate(credentials)
      .subscribe(
        success => {
          this.router.navigate(['../user-home/user-property']);
        },
        error => {
          if (error.status === 401) {
            this.loginForm.get('password').setErrors({ invalid: 'A megadott e-mail/felhasználónév - jelszó páros érvénytelen!' });
          }
        }
      );
  }
}
