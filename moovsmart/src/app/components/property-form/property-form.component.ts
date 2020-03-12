import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import { PropertyService } from '../../services/property.service';
import { Router } from '@angular/router';
import { validationHandler } from '../../utils/validationHandler';
import {PropertyTypeOptionItemModel} from "../../models/propertyTypeOptionItem.model";
import {PropertyConditionTypeOptionItemModel} from "../../models/propertyConditionTypeOptionItem.model";
import {ParkingTypeOptionItemModel} from "../../models/parkingTypeOptionItem.model";
import {FormInitDataModel} from "../../models/formInitDataModel";
import {MapsAPILoader} from "@agm/core";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-property-form',
  templateUrl: './property-form.component.html',
  styleUrls: ['./property-form.component.css']
})
export class PropertyFormComponent implements OnInit {
  propertyType: Array<PropertyTypeOptionItemModel>;
  propertyConditionType: Array<PropertyConditionTypeOptionItemModel>;
  parkingType: Array<ParkingTypeOptionItemModel>;

    private geoCoder;
    zoom: number;
    address: string;
    city: string;
    district: string;
    street: string;
    postalCode: string;
    addressId: string;
    addressComponent: any;

    @ViewChild('search')
    public searchElementRef: ElementRef;

  //

    propertyForm = this.formBuilder.group({
    price: [0 ,Validators.required],
    listOfImages: [null],

    propertyTypes: ['',Validators.required],
    propertyConditionTypes: ['',Validators.required],
    propertyConstructionTypes: ['',Validators.required],
    parkingTypes: ['',Validators.required],
    title: ['',Validators.required],


    area: [0,Validators.required],
    numberOfRooms: [0,Validators.required],

    elevator: [false],
    balcony: [false],

    description: ['',Validators.required],

    advertStatus: ['FORAPPROVAL'],

      address: [''],
      city: [''],
      street: [''],
      district: [''],
      postalCode: ['']


  });


  constructor(
    private formBuilder: FormBuilder,
    private propertyService: PropertyService,
    private router: Router,

    private httpClient: HttpClient,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone
  ) { }

  ngOnInit() {
    this.propertyService.fetchFormInitData().subscribe(
      (initData: FormInitDataModel) =>{
        this.propertyType = initData.propertyTypes;
        this.propertyConditionType = initData.propertyConditionTypes;
        this.parkingType = initData.parkingTypes;
      },
      error => console.warn(error)
    )


    //load Places Autocomplete
    this.mapsAPILoader.load().then(() => {

      this.geoCoder = new google.maps.Geocoder;

      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        types: ["address"]
      });
      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          //get the place result
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();

          //verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          //set latitude, longitude and zoom
          //this.latitude = place.geometry.location.lat();
          //this.longitude = place.geometry.location.lng();
          this.address = place.formatted_address;
          this.addressId = place.place_id;
          this.addressComponent = place.address_components;
          console.log(this.addressComponent.length);
          console.log(this.addressComponent);


          for(let i = 0; i < this.addressComponent.length; i++){
            switch (this.addressComponent[i].types[0]) {
              case "route": {
                this.street = place.address_components[i].long_name;
                break;
              }
              case "sublocality_level_1":{
                this.district = place.address_components[i].long_name;
                break;
              }
              case "locality": {
                this.city = place.address_components[i].long_name;
                break;
              }
              case "postal_code": {
                this.postalCode = place.address_components[i].long_name;
                break;
              }
            }
          }

          this.zoom = 12;

        });
      });
    });



  }



  submit = () =>
    this.propertyService.createProperty(this.propertyForm.value).subscribe(
      () => this.router.navigate(['property-list']),
      error => validationHandler(error, this.propertyForm),
    )

  clearAddressDetails() {
    this.street='';
    this.postalCode='';
    this.address='';
    this.city='';
    this.district='';
  }
}
