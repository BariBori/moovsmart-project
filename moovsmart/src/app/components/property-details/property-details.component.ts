import {Component, Input, NgZone, OnInit, ViewChild} from '@angular/core';
import {PropertyAdvertDetailsModel} from "../../models/propertyAdvertDetails.model";
import {PropertyService} from "../../services/property.service";
import {ActivatedRoute, Router} from "@angular/router";
import {faStar} from '@fortawesome/free-regular-svg-icons';
import {MapsAPILoader} from "@agm/core";
import {PropertyListItemModel} from "../../models/propertyListItem.model";
import {faEnvelope} from "@fortawesome/free-solid-svg-icons";


@Component({
  selector: 'app-property-details',
  templateUrl: './property-details.component.html',
  styleUrls: ['./property-details.component.css']
})
export class PropertyDetailsComponent implements OnInit {

  id: string;
  propertyAdvertDetails: PropertyAdvertDetailsModel;
  propertyListItemModels: Array<PropertyListItemModel>;

  faStar= faStar;
  faEnvelope = faEnvelope;

  public latitude: number;
  public longitude: number;
  public zoom: number = 15;
  public map: google.maps.Marker;


  constructor(private route: ActivatedRoute,
              private propertyAdvertService: PropertyService,
              private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone,
              private router: Router,
              ) {

     }

  ngOnInit(): void {

    this.route.paramMap.subscribe(
      paramMap => {
        const id = paramMap.get('id');
        if (id) {
          this.id = id;
          this.loadPropertyAdvertDetails();

        }
      },
    );
  }

  archivePropertyAdvert(id: number) {
    this.propertyAdvertService.archivePropertyAdvert(id).subscribe(
      (response: PropertyListItemModel[]) => {
        this.propertyListItemModels = response;
      },
      error => console.warn(error),
    );
  }


  loadPropertyAdvertDetails() {
    this.propertyAdvertService.fetchAdvertDetails(this.id).subscribe(
      (data: PropertyAdvertDetailsModel) => this.propertyAdvertDetails = data,
      error => console.warn(error),
    );
  }

  //--------Google map------//


  setLocation(map) {
    this.propertyAdvertService.fetchAdvertDetails(this.id).subscribe(
      (data: PropertyAdvertDetailsModel) => this.propertyAdvertDetails = data,
      error => console.warn(error),
      () => {
        this.latitude = this.propertyAdvertDetails?.latitude;
        this.longitude = this.propertyAdvertDetails?.longitude;
        this.map = new google.maps.Marker({
          position: { lat: this.latitude, lng: this.longitude },
          map: map});

      }
    );

  }


  editProperty(id: number) {
    this.router.navigate(['/property-form',id]);
  }
}
