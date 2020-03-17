import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ValidationErrorRendererService {

  constructor() { }
  private form: FormGroup;
  public setForm(form: FormGroup) { this.form = form; }
  public renderValidationErrors(formControlName: string) {
    return Object.values(this.form
      .get(formControlName)
      ?.errors)
      ?.filter(errorMessage => (errorMessage instanceof String))
      .join('\n');
  }
}
