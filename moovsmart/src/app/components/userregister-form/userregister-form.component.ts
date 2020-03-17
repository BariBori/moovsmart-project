import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserFormDataModel } from '../../models/userFormData.model';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { validationHandler } from 'src/app/utils/validationHandler';
import { FormValidationError } from 'src/app/models/error/FormValidationError';
import { ValidationErrorRendererService } from 'src/app/services/validation-error-renderer.service';

@Component({
  selector: 'app-userregister-form',
  templateUrl: './userregister-form.component.html',
  styleUrls: ['./userregister-form.component.css']
})
export class UserregisterFormComponent implements OnInit {

  registerNewUserForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    public validationErrorRenderer: ValidationErrorRendererService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.registerNewUserForm = this.formBuilder.group({
      email: ['', Validators.email],
      password: ['', Validators.minLength(4)],
      userName: ['', Validators.minLength(3)],
      personalDetails: [null],
      privacyPolicy: [null, Validators.requiredTrue],
      termsConditions: [null, Validators.requiredTrue]
    });
    this.validationErrorRenderer.setForm(this.registerNewUserForm);
  }

  saveUser() {
    const formData: UserFormDataModel = this.registerNewUserForm.value;
    this.userService.createUser(formData).subscribe(
      (response) => {
        this.router.navigate(['']);
        console.log('New user is created');
      },
      errorResponse => {
        validationHandler(errorResponse, this.registerNewUserForm);
      }
    );
  }

}
