import { HttpErrorResponse } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { MoovSmartValidationError } from '../models/error/MoovSmartError';

export function validationHandler(moovSmartValidationError: HttpErrorResponse & MoovSmartValidationError, form: FormGroup) {
  for (const field in moovSmartValidationError.error.fieldErrors) {
    form.get(field)?.setErrors(moovSmartValidationError[field]);
  }
}
