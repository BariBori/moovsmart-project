import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ValidatorFn, AbstractControl } from '@angular/forms';
import { UserFormDataModel } from '../../models/userFormData.model';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { validationHandler } from 'src/app/utils/validationHandler';
import { FormValidationError } from 'src/app/models/error/FormValidationError';

@Component({
  selector: 'app-userregister-form',
  templateUrl: './userregister-form.component.html',
  styleUrls: ['./userregister-form.component.css']
})
export class UserregisterFormComponent implements OnInit {

  registerNewUserForm: FormGroup;

  private checkPasswords: ValidatorFn = (group: FormGroup) =>
    group.get('password').value
      === group.get('passwordConfirm').value
      ? null
      : { passwordMismatch: 'A megadott jelszavak nem egyeznek!' }


  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {

    this.registerNewUserForm = this.formBuilder.group({
      email: [''],
      password: [''],
      passwordConfirm: ['', this],
      userName: [''],
      personalDetails: [null],
      privacyPolicy: [null, Validators.requiredTrue],
      termsConditions: [null, Validators.requiredTrue]
    }, { validator: this.checkPasswords });
  }

  saveUser() {
    const formData: UserFormDataModel = this.registerNewUserForm.value;
    this.userService.createUser(formData).subscribe(
      (response) => {
        this.router.navigate(['']);
        console.log('New user is created');
      },
      errorResponse => {
        validationHandler(errorResponse as FormValidationError, this.registerNewUserForm);
      }
    );
  }

}
