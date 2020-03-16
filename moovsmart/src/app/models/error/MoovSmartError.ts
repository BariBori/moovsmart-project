import { HttpErrorResponse } from '@angular/common/http';
import { ValidationErrors } from '@angular/forms';

export interface MoovSmartValidationError {
    error: ValidationErrors;
}
