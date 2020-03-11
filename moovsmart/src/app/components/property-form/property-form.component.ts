import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import { PropertyService } from '../../services/property.service';
import { Router } from '@angular/router';
import { validationHandler } from '../../utils/validationHandler';
import {PropertyTypeOptionItemModel} from "../../models/propertyTypeOptionItem.model";
import {PropertyConditionTypeOptionItemModel} from "../../models/propertyConditionTypeOptionItem.model";
import {ParkingTypeOptionItemModel} from "../../models/parkingTypeOptionItem.model";
import {FormInitDataModel} from "../../models/formInitDataModel";

@Component({
  selector: 'app-property-form',
  templateUrl: './property-form.component.html',
  styleUrls: ['./property-form.component.css']
})
export class PropertyFormComponent implements OnInit {
    propertyTypes: Array<PropertyTypeOptionItemModel>;
    propertyConditionTypes: Array<PropertyConditionTypeOptionItemModel>;
    propertyConstructionTypes: Array<PropertyConditionTypeOptionItemModel>;
    parkingTypes: Array<ParkingTypeOptionItemModel>;


  propertyForm = this.formBuilder.group({
    price: [0 ,Validators.required],
    listOfImages: [null],

    propertyType: ['',Validators.required],
    propertyConditionType: ['',Validators.required],
    propertyConstructionType: ['',Validators.required],
    parkingType: ['',Validators.required],
    title: ['',Validators.required],

    address: ['',Validators.required],
    city: [''],
    district: [''],
    street: [''],

    area: [0,Validators.required],
    numberOfRooms: [0,Validators.required],

    elevator: ['',Validators.required],
    balcony: ['',Validators.required],

    description: ['',Validators.required],

    status: ['Jóváhagyás alatt'],
    activationDate: ['']

  });

  constructor(
    private formBuilder: FormBuilder,
    private propertyService: PropertyService,
    private router: Router
  ) { }

  ngOnInit() {
    this.propertyService.fetchFormInitData().subscribe(
      (initData: FormInitDataModel) =>{
        this.propertyTypes = initData.propertyTypes;
        this.propertyConditionTypes = initData.propertyConditionTypes;
        this.propertyConstructionTypes = initData.propertyConstructionTypes;
        this.parkingTypes = initData.parkingTypes;
      },
      error => console.warn(error)
    )
  }

  submit = () =>
    this.propertyService.createProperty(this.propertyForm.value).subscribe(
      () => this.router.navigate(['property-list']),
      error => validationHandler(error, this.propertyForm),
    )

}
