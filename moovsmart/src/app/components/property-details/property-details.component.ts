import {Component, Input, NgZone, OnInit, ViewChild} from '@angular/core';
import {PropertyAdvertDetailsModel} from "../../models/propertyAdvertDetails.model";
import {PropertyService} from "../../services/property.service";
import {ActivatedRoute, Router} from "@angular/router";
import {faStar} from '@fortawesome/free-regular-svg-icons';
import {MapsAPILoader} from "@agm/core";


@Component({
  selector: 'app-property-details',
  templateUrl: './property-details.component.html',
  styleUrls: ['./property-details.component.css']
})
export class PropertyDetailsComponent implements OnInit {

  advertId: number;
  propertyAdvertDetails: PropertyAdvertDetailsModel;

  faStar= faStar;

  public latitude: number;
  public longitude: number;
  public zoom: number = 15;

  constructor(private route: ActivatedRoute,
              private propertyAdvertService: PropertyService,
              private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone
              ) {

     }

  ngOnInit(): void {

    this.route.paramMap.subscribe(
      paramMap => {
        const id: number = Number(paramMap.get('id'));
        if (id) {
          this.advertId = id;
          this.loadPropertyAdvertDetails();
        }
      },
    );
  }


  loadPropertyAdvertDetails() {
    this.propertyAdvertService.fetchAdvertDetails(this.advertId).subscribe(
      (data: PropertyAdvertDetailsModel) => this.propertyAdvertDetails = data,
      error => console.warn(error),
    );
  }

  //--------Google map------//
  /*mapReady($event: google.maps.Map) {
    this.latitude = this.propertyAdvertDetails?.latitude;
    this.longitude = this.propertyAdvertDetails?.longitude;
  }*/

  setLocation() {
    this.latitude = this.propertyAdvertDetails?.latitude;
    this.longitude = this.propertyAdvertDetails?.longitude;
  }





}
