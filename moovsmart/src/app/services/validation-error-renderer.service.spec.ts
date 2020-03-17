import { TestBed } from '@angular/core/testing';

import { ValidationErrorRendererService } from './validation-error-renderer.service';

describe('ValidationErrorRendererService', () => {
  let service: ValidationErrorRendererService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ValidationErrorRendererService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
