import { HttpErrorResponse } from '@angular/common/http';
import { ValidationErrors } from '@angular/forms';

export interface FormValidationError {
    error: {
        formControlErrors: {
            [formControlName: string]: ValidationErrors
        }
    };
}
