import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from 'src/app/services/authentication.service';
import {Credentials} from 'src/app/models/Credentials';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {


  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [''],
      password: [''],
    });
  }

  onSubmit(): void {
    const credentials = this.loginForm.value as Credentials;
    this.authService.authenticate(credentials)
      .subscribe(
        success => {
          this.authService.credentials = credentials;
          this.router.navigate(['user-home']);
        },
        failure => console.warn(failure),
        console.log
      );
  }
}
