import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {PropertyTypeOptionItemModel} from "../../../models/propertyTypeOptionItem.model";
import {PropertyConditionTypeOptionItemModel} from "../../../models/propertyConditionTypeOptionItem.model";
import {ParkingTypeOptionItemModel} from "../../../models/parkingTypeOptionItem.model";
import {PropertyService} from "../../../services/property.service";
import {FormInitDataModel} from "../../../models/formInitDataModel";
import {PropertyCityModel} from "../../../models/propertyCity.model";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  form: FormGroup;
  @Output() autoSearch: EventEmitter<string> = new EventEmitter<string>();
  @Output() groupFilters : EventEmitter<any> = new EventEmitter<any>();
  searchText: string = '';

  propertyType: PropertyTypeOptionItemModel[];
  propertyConditionType: PropertyConditionTypeOptionItemModel[];
  parkingType: ParkingTypeOptionItemModel[];
  cities: any;
  cityNameList: Array<string> = [];
  hasImage: boolean;
  typeahead: any;


  suggestions: Array<string>;
  selectedCityName: string;

  constructor(private formBuilder: FormBuilder,
              private propertyService: PropertyService) { }

  ngOnInit(): void {



    this.propertyService?.getCityList().subscribe(
      (cityData: any) =>{
        this.cities = cityData;
        console.log(cityData);
        console.log(this.cities);
        this.makeCityList();
        console.log(this.cityNameList);
        console.log(this.suggestions);
      }
    );

    this.propertyService.fetchFormInitData().subscribe(
      (initData: FormInitDataModel) => {
        this.propertyType = initData.propertyType;
        this.propertyConditionType = initData.propertyConditionType;
        this.parkingType = initData.parkingType;
      });



    this.buildForm();
  }

  makeCityList(){
    for (let index of this.cities){
      this.cityNameList?.push(index.city);
    }
    return this.cityNameList;
    console.log(this.cityNameList);
  }

  suggest(){
    this.suggestions = this.cityNameList
      .filter(c => c.toLowerCase().startsWith(this.typeahead.value.toLowerCase()))
      .slice(0, 25);
  }

  chooseCityFromSuggestList(city){
    this.selectedCityName = city;
    this.suggestions.length = 0;
    this.typeahead.setValue(city);

  }


  buildForm():void{
    this.form = this.formBuilder.group({
      cities: new FormControl(''),
      propertyType: new FormControl(''),
      propertyConditionType: new FormControl(''),
      numberOfRoomsFrom: new FormControl(''),
      numberOfRoomsTo: new FormControl(''),
      priceFrom: new FormControl(''),
      priceTo: new FormControl(''),
      areaFrom: new FormControl(''),
      areaTo: new FormControl(''),
      hasImage: new FormControl(''),
      typeahead: new FormControl()

    })
  }


  search(filters: any): void {
    Object.keys(filters).forEach(key => filters[key] === '' ? delete filters[key] : key);
    this.groupFilters.emit(filters);
  }

}
