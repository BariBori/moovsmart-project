import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { UserFormDataModel } from 'src/app/models/userFormData.model';

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
  ) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [''],
      password: [''],
    });
  }

  onSubmit() {
    const formData: UserFormDataModel = this.loginForm.value;
    this.userService.createUser(formData).subscribe(
      (response) => {
        this.router.navigate(['']);
        console.log('New user is created');
      },
      error => {
        console.warn(error);
        //validationHandler(error, this.createNewBlogPost);
      });
  }
}
