import { Component, Input, NgZone, OnInit, ViewChild } from '@angular/core';
import { PropertyAdvertDetailsModel } from "../../models/propertyAdvertDetails.model";
import { PropertyService } from "../../services/property.service";
import { ActivatedRoute, Router } from "@angular/router";
import { faStar as fasStar } from '@fortawesome/free-regular-svg-icons';
import { MapsAPILoader } from "@agm/core";
import { PropertyListItemModel } from "../../models/propertyListItem.model";
import { faEnvelope, faStar as farStar } from "@fortawesome/free-solid-svg-icons";
import { UserService } from "../../services/user.service";
import { tap } from "rxjs/operators";
import { MessagingService } from 'src/app/services/messaging.service';
import { Observable } from 'rxjs';
import DateTimeFormat = Intl.DateTimeFormat;
import {BidService} from "../../services/bid.service";
import {BidFormDataModel} from "../../models/bids/bidFormData.model";
import {BidFormComponent} from "../bid-form/bid-form.component";
import {BidListItemModel} from "../../models/bids/bidListItem.model";


@Component({
  selector: 'app-property-details',
  templateUrl: './property-details.component.html',
  styleUrls: ['./property-details.component.css']
})
export class PropertyDetailsComponent implements OnInit {

  bidListItemModel: Array<BidListItemModel>;
  lastBidArray: Array<BidListItemModel>;
  lastBid: number;
  nextBid: string;
  actualPrice: number;

  id: string;
  propertyAdvertDetails: PropertyAdvertDetailsModel;
  propertyListItemModels: Array<PropertyListItemModel>;
  isUserSeller = false;
  isVisitorLogged = false;
  favourites: PropertyListItemModel[] = [];

  fasStar = fasStar;
  farStar = farStar;
  faEnvelope = faEnvelope;

  today = Date.now();

  public latitude: number;
  public longitude: number;
  public zoom: number = 15;
  public map: google.maps.Marker;
  public userName: string;

  public now: number = Date.now();

  constructor(
    private route: ActivatedRoute,
    private propertyAdvertService: PropertyService,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone,
    private router: Router,
    private userService: UserService,
    private messagingService: MessagingService,
    private bidService: BidService,

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
          this.getLastBid()
        }
      },
    );

  console.log(this.today);

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

  getLastBid(){
    this.bidService.getBidList(Number(this.id)).subscribe(
      amount => {
        this.lastBidArray = amount.slice(0,1);
        this.lastBid = this.lastBidArray[0].amountOfBid;
        this.nextBid = (this.lastBid + 0.1).toFixed(1);
        console.log(this.lastBid);
      },

    )
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

  isShow = true;
  toggleDisplay() {
    this.isShow = !this.isShow;
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

  isFavourite() {
    return this.favourites?.some(fav => fav?.id === this.propertyAdvertDetails?.id);
  }

  saveUnsaveFavourite() {
    const id = this.propertyAdvertDetails.id;
    if (this.isFavourite()) {
      this.propertyAdvertService.removeFavouriteAdvert(id).subscribe(
        console.log
      );
    } else {
      this.propertyAdvertService.saveFavouriteAdvert(id).subscribe(
        console.log
      );
    }
  }
}
