import { Component, Input, NgZone, OnInit, ViewChild } from '@angular/core';
import { PropertyAdvertDetailsModel } from "../../models/propertyAdvertDetails.model";
import { PropertyService } from "../../services/property.service";
import { ActivatedRoute, Router } from "@angular/router";
import { faStar } from '@fortawesome/free-regular-svg-icons';
import { MapsAPILoader } from "@agm/core";
import { PropertyListItemModel } from "../../models/propertyListItem.model";
import { faEnvelope } from "@fortawesome/free-solid-svg-icons";
import { UserService } from "../../services/user.service";
import { tap } from "rxjs/operators";
import { MessagingService } from 'src/app/services/messaging.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-property-details',
  templateUrl: './property-details.component.html',
  styleUrls: ['./property-details.component.css']
})
export class PropertyDetailsComponent implements OnInit {

  id: string;
  propertyAdvertDetails: PropertyAdvertDetailsModel;
  propertyListItemModels: Array<PropertyListItemModel>;
  isUserSeller = false;
  isVisitorLogged = false;
  favourites: PropertyListItemModel[] = [];

  faStar = faStar;
  faEnvelope = faEnvelope;

  public latitude: number;
  public longitude: number;
  public zoom: number = 15;
  public map: google.maps.Marker;
  public userName: string;


  constructor(private route: ActivatedRoute,
    private propertyAdvertService: PropertyService,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone,
    private router: Router,
    private userService: UserService,
    private messagingService: MessagingService
  ) {
  }

  ngOnInit(): void {
    this.propertyAdvertService.savedAdverts.pipe(tap(console.log)).subscribe(saved => this.favourites = saved);
    this.route.paramMap.subscribe(
      paramMap => {
        const id = paramMap.get('id');
        if (id) {
          this.id = id;
          this.loadPropertyAdvertDetails();
          this.getVisitorLogged();
        }
      },
    );


  }

  archivePropertyAdvert(id: number) {
    this.propertyAdvertService.archivePropertyAdvert(id).subscribe(
      (response: PropertyListItemModel[]) => {
        this.propertyListItemModels = response;
        this.router.navigate(['property-list']);
      },
      error => console.warn(error),
    );
  }


  loadPropertyAdvertDetails() {
    this.propertyAdvertService.fetchAdvertDetails(this.id).pipe(
      tap(details => this.userService.getCurrentUser
        .subscribe(user =>
          this.isUserSeller = (user.userName === details.userName)
        )
      )
    ).subscribe(
      (data: PropertyAdvertDetailsModel) => this.propertyAdvertDetails = data,

      error => console.warn(error),
    );

  }
  sendMessage() {
    this.userService.isLoggedIn()
      ? this.messagingService.beginDirectMessaging(Number(this.id))
        .subscribe(success => this.router.navigate(['/messaging']))
      : this.router.navigate(['user-login']);
  }

  getVisitorLogged() {
    if (this.userService.isLoggedIn()) {
      this.isVisitorLogged = true;
    } else {
      this.isVisitorLogged = false;
    }

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
          map: map
        });

      }
    );

  }


  editProperty(id: number) {
    this.router.navigate(['/property-form', id]);
  }

  saveFavourite(advertId: number) {
    this.propertyAdvertService.saveFavouriteAdvert(advertId).subscribe(
      console.log
    );
  }
  isFavourite() {
    this.favourites.some(fav => fav.id === this.propertyAdvertDetails.id)
  }
}
