import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validator, ValidatorFn, Validators} from "@angular/forms";
import {PropertyTypeOptionItemModel} from "../../../models/propertyTypeOptionItem.model";
import {PropertyConditionTypeOptionItemModel} from "../../../models/propertyConditionTypeOptionItem.model";
import {PropertyService} from "../../../services/property.service";
import {FormInitDataModel} from "../../../models/formInitDataModel";
import {StringBuilder} from "./string-builder";
import {validate} from "codelyzer/walkerFactory/walkerFn";
import {areaValidator, priceValidator, roomValidator} from "./validator.directive";




@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  form: FormGroup;
  searchText: string = '';
  searchSpec = new StringBuilder();


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
              private propertyService: PropertyService) { }

  searchFrom = this.formBuilder.group({
    city: [''],
    propertyType: [''],
    propertyConditionType: [''],
    minRooms: [null, Validators.required],
    maxRooms: [null, Validators.required],
    minPrice: [null, Validators.required],
    maxPrice:  [null, Validators.required],
    minArea: [null, Validators.required],
    maxArea:  [null, Validators.required],

  }, {validators:Validators.compose([areaValidator, priceValidator, roomValidator])}

  );

  searchForm = this.formBuilder.array([], Validators.compose([Validators.required, Validators.minLength(3)]));



  ngOnInit(): void {
    this.propertyService?.getCityList().subscribe(
      (cityData: any) =>{
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

  makeCityList(){
    for (let index of this.city){
      this.cityNameList?.push(index.city);
    }
    return this.cityNameList;
    console.log(this.cityNameList);
  }

  validateMaxPrice(maxPrice: number){
    if(this.maxPrice === null){
      this.maxPrice = 99999999.0;
    }
  }

  validateMinPrice(minPrice: number){
    if(this.minPrice === null){
      this.minPrice = 0.0;
    }
  }

  validateMaxArea(maxArea: number){
    if(this.maxPrice  === null){
      this.maxPrice = 999999999;
    }
  }

  validateMinArea(minArea: number){
    if(this.minArea === null){
      this.minArea = 0;
    }
  }

  validateMaxRooms(maxRooms: number){
    if(this.maxRooms === null){
      this.maxRooms = 500;
    }
  }

  validateMinRooms(minRooms: number){
    if(this.minRooms === null){
      this.maxRooms = 0;
    }
  }

submit = () => {
    let filterPropertyAdvertModel = this.searchFrom.value;
    /*filterPropertyAdvertModel.city = this.city;
    filterPropertyAdvertModel.maxPrice = this.validateMaxPrice(this.maxPrice);
    filterPropertyAdvertModel.minPrice = this.validateMinPrice(this.minPrice);
    filterPropertyAdvertModel.maxArea = this.validateMaxPrice(this.maxArea);
    filterPropertyAdvertModel.minArea = this.validateMinArea(this.minArea);
    filterPropertyAdvertModel.maxRooms = this.validateMaxRooms(this.maxRooms);
    filterPropertyAdvertModel.minRooms = this.validateMinRooms(this.minRooms);
    filterPropertyAdvertModel.propertyType = this.propertyType;
    filterPropertyAdvertModel.propertyConditionType = this.propertyConditionType;*/

    console.log(filterPropertyAdvertModel);
    /*this.propertyService.postFilteredPropertyAdverts(filterPropertyAdvertModel).subscribe(
      () => console.log(filterPropertyAdvertModel)
    )*/

}






}
