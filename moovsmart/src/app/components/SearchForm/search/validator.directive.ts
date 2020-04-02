import {FormGroup, ValidationErrors, ValidatorFn} from "@angular/forms";

export const priceValidator: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
  const minPrice = control.get('minPrice');
  const maxPrice = control.get('maxPrice');

  return (minPrice.value !==null && maxPrice.value !==null) && maxPrice.value < minPrice.value ? { 'priceRangeError': true } : null;
};

export const areaValidator: ValidatorFn = (control2: FormGroup): ValidationErrors | null => {
  const minArea = control2.get('minArea');
  const maxArea = control2.get('maxArea');

  return (minArea.value!==null && maxArea.value!==null) && maxArea.value < minArea.value ? { 'areaRangeError': true } : null;
};

export const roomValidator: ValidatorFn = (control3: FormGroup): ValidationErrors | null => {
  const minRooms = control3.get('minRooms');
  const maxRooms = control3.get('maxRooms');

  return (minRooms.value!==null && maxRooms.value!==null) && maxRooms.value < minRooms.value ? { 'numberOfRoomsRangeError': true } : null;
};



