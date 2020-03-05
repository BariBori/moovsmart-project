import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserFormDataModel} from "../../models/userFormData.model";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-userregister-form',
  templateUrl: './userregister-form.component.html',
  styleUrls: ['./userregister-form.component.css']
})
export class UserregisterFormComponent implements OnInit {

  registerNewUserForm: FormGroup;

  constructor(private formGroup: FormGroup, private formBuilder: FormBuilder, private userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.registerNewUserForm = this.formBuilder.group({
      email: [''],
      password: [''],
      personalDetails: [null],
    })
  }

  saveUser() {
    let formData: UserFormDataModel = this.registerNewUserForm.value;
    this.userService.createUser(formData).subscribe(
      (response) => {
        this.router.navigate(['']);
        console.log('New user is created');
      },
      error => {
        console.warn(error);
        //validationHandler(error, this.createNewBlogPost);
      })
  }

}
