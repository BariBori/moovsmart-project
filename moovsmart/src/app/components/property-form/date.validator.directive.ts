import {FormGroup, ValidationErrors, ValidatorFn} from '@angular/forms';

export const dateValidator: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
  const startOfAuction = control.get('startOfAuction');
  const endOfAuction = control.get('endOfAuction');

  return (startOfAuction !== null && endOfAuction !== null) &&
  (endOfAuction.value.getTime() < startOfAuction.value.getTime()) ? { dateRangeError: true } : null;
};
