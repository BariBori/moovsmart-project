import {FormGroup, ValidationErrors, ValidatorFn} from '@angular/forms';


export const priceValidator: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
  const minPrice = control.get('minPrice');
  const maxPrice = control.get('maxPrice');

  return (minPrice.value !== null && maxPrice.value !== null) && maxPrice.value < minPrice.value ? { priceRangeError: true } : null;
};

export const areaValidator: ValidatorFn = (control2: FormGroup): ValidationErrors | null => {
  const minArea = control2.get('minArea');
  const maxArea = control2.get('maxArea');

  return (minArea.value !== null && maxArea.value !== null) && maxArea.value < minArea.value ? { areaRangeError: true } : null;
};

export const roomValidator: ValidatorFn = (control3: FormGroup): ValidationErrors | null => {
  const minRooms = control3.get('minRooms');
  const maxRooms = control3.get('maxRooms');

  return (minRooms.value !== null && maxRooms.value !== null) && maxRooms.value < minRooms.value ? { numberOfRoomsRangeError: true } : null;
};

export const minPriceValidator: ValidatorFn = (controlMinPrice: FormGroup): ValidationErrors | null => {
    const minPrice = controlMinPrice.get('minPrice');
    return minPrice.value != null && minPrice.value < 0 ? {minPriceError: true} : null;
};

export const maxPriceValidator: ValidatorFn = (controlMaxPrice: FormGroup): ValidationErrors | null => {
  const maxPrice = controlMaxPrice.get('maxPrice');
  return maxPrice.value != null && maxPrice.value < 0 ? {maxPriceError: true} : null;
};

export const minRoomsValidator: ValidatorFn = (controlMinRooms: FormGroup): ValidationErrors | null => {
  const minRooms = controlMinRooms.get('minRooms');
  return minRooms.value != null && minRooms.value < 0 ? {minRoomsError: true} : null;
};

export const maxRoomsValidator: ValidatorFn = (controlMaxRooms: FormGroup): ValidationErrors | null => {
  const maxRooms = controlMaxRooms.get('maxRooms');
  return maxRooms.value != null && maxRooms.value < 0 ? {maxRoomsError: true} : null;
};

export const minAreaValidator: ValidatorFn = (controlMinArea: FormGroup): ValidationErrors | null => {
  const minArea = controlMinArea.get('minArea');
  return minArea.value != null && minArea.value < 0 ? {minAreaError: true} : null;
};

export const maxAreaValidator: ValidatorFn = (controlMaxArea: FormGroup): ValidationErrors | null => {
  const maxArea = controlMaxArea.get('maxArea');
  return maxArea.value != null && maxArea.value < 0 ? {maxAreaError: true} : null;
};

