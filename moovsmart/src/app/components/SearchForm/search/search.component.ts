import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {PropertyTypeOptionItemModel} from "../../../models/propertyTypeOptionItem.model";
import {PropertyConditionTypeOptionItemModel} from "../../../models/propertyConditionTypeOptionItem.model";
import {PropertyService} from "../../../services/property.service";
import {FormInitDataModel} from "../../../models/formInitDataModel";
import {StringBuilder} from "./string-builder";

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
  searchSpec = new StringBuilder();


  propertyType: PropertyTypeOptionItemModel[];
  propertyConditionType: PropertyConditionTypeOptionItemModel[];
  cities: any;
  cityNameList: Array<string> = [];
  numberOfRoomsFrom: number;
  numberOfRoomsTo: number;
  priceFrom: number;
  priceTo: number;
  areaFrom: number;
  areaTo: number;


  constructor(private formBuilder: FormBuilder,
              private propertyService: PropertyService) { }

  searchFrom = this.formBuilder.group({
    cities: [''],
    propertyType: [''],
    propertyConditionType: [''],
    numberOfRoomsFrom: [null],
    numberOfRoomsTo: [null],
    priceFrom: [null],
    priceTo:  [null],
    areaFrom: [null],
    areaTo:  [null],

  });

  ngOnInit(): void {
    this.propertyService?.getCityList().subscribe(
      (cityData: any) =>{
        this.cities = cityData;
        console.log(cityData);
        console.log(this.cities);
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
    for (let index of this.cities){
      this.cityNameList?.push(index.city);
    }
    return this.cityNameList;
    console.log(this.cityNameList);
  }






buildSearchText() : string {

if(this.cities != '' || this.cities != null){
  this.searchSpec.append("city:" + this.cities);
}

if(this.propertyType.toString() != '' || this.propertyType != null){
  this.searchSpec.append("propertyType:" + this.propertyType);
}

if(this.propertyConditionType.toString()!='' || this.propertyConditionType != null){
  this.searchSpec.append("propertyConditionType:" + this.propertyConditionType);
}

if(this.numberOfRoomsFrom != null){
  this.searchSpec.append("numberOfRooms>"+ (this.numberOfRoomsFrom-1));
}

  if(this.numberOfRoomsTo != null){
    this.searchSpec.append("numberOfRooms<"+ (this.numberOfRoomsTo+1));
  }

  if(this.priceFrom !=null){
    this.searchSpec.append("price>" + this.priceFrom);
  }

  if(this.priceTo != null){
    this.searchSpec.append("price<" + this.priceTo);
  }

  if(this.areaFrom != null){
    this.searchSpec.append("area>" + this.areaFrom);
  }

  if(this.areaTo != null){
    this.searchSpec.append("area<" + this.areaTo);
  }

  this.searchText = this.searchSpec.toString();
return this.searchText;
  console.log(this.searchText);
}

onSubmit(){
    this.propertyService.getSearchResult(this.searchText);
}

}
