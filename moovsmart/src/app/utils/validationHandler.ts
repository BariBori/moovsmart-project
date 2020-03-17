import { FormGroup, FormControlName } from '@angular/forms';
import { FormValidationError } from '../models/error/FormValidationError';

export function validationHandler(moovSmartValidationError: FormValidationError, form: FormGroup) {
  const formControlErrors = moovSmartValidationError.error.formControlErrors;

  Object.keys(formControlErrors).forEach(formControlName =>
    form
      .get(formControlName)
      .setErrors(formControlErrors[formControlName])
  );
}
