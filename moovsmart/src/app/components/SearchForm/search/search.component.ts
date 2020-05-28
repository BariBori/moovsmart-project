import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validator, ValidatorFn, Validators} from '@angular/forms';
import {PropertyTypeOptionItemModel} from '../../../models/propertyTypeOptionItem.model';
import {PropertyConditionTypeOptionItemModel} from '../../../models/propertyConditionTypeOptionItem.model';
import {PropertyService} from '../../../services/property.service';
import {FormInitDataModel} from '../../../models/formInitDataModel';
import {
  areaValidator, maxAreaValidator,
  maxPriceValidator, maxRoomsValidator, minAreaValidator,
  minPriceValidator, minRoomsValidator,
  priceValidator,
  roomValidator
} from './validator.directive';
import {SharingSearchService} from '../../../services/sharing-search.service';






@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  form: FormGroup;

  propertyType: PropertyTypeOptionItemModel[];
  propertyConditionType: PropertyConditionTypeOptionItemModel[];
  city: any;
  cityNameList: Array<string> = [];
  minRooms: number;
  maxRooms: number;
  minPrice: number;
  maxPrice: number;
  minArea: number;
  maxArea: number;


  constructor(private formBuilder: FormBuilder,
              private propertyService: PropertyService,
              private sharingSearchService: SharingSearchService) { }

  searchFrom = this.formBuilder.group({
    city: [''],
    propertyType: [],
    propertyConditionType: [],
    minRooms: [null, Validators.required],
    maxRooms: [null, Validators.required],
    minPrice: [null, Validators.required],
    maxPrice:  [null, Validators.required],
    minArea: [null, Validators.required],
    maxArea:  [null, Validators.required],

  }, {validators: Validators.compose(
    [areaValidator, priceValidator, roomValidator,
              minPriceValidator, maxPriceValidator,
              minRoomsValidator, maxRoomsValidator,
              minAreaValidator, maxAreaValidator])}
  );


  ngOnInit(): void {
    this.propertyService?.getCityList().subscribe(
      (cityData: any) => {
        this.city = cityData;
        console.log(cityData);
        console.log(this.city);
        this.makeCityList();
        console.log(this.cityNameList);

      }
    );

    this.propertyService.fetchFormInitData().subscribe(
      (initData: FormInitDataModel) => {
        this.propertyType = initData.propertyType;
        this.propertyConditionType = initData.propertyConditionType;
      });

  }

  makeCityList() {
    for (const index of this.city) {
      this.cityNameList?.push(index.city);
    }
    return this.cityNameList;
    console.log(this.cityNameList);
  }

  submit(filterPropertyAdvertModel) {
    this.sharingSearchService.filteredProperties.next(filterPropertyAdvertModel);
  }

}
