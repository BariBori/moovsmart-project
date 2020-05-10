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

import {DatePipe} from "@angular/common";


@Component({
  selector: 'app-property-details',
  templateUrl: './property-details.component.html',
  styleUrls: ['./property-details.component.css']
})
export class PropertyDetailsComponent implements OnInit {

  lastBidAmount: number;
  nextBid: string;

  id: string;
  propertyAdvertDetails: PropertyAdvertDetailsModel;
  propertyListItemModels: Array<PropertyListItemModel>;
  isUserSeller = false;
  isVisitorLogged = false;
  favourites: PropertyListItemModel[] = [];

  fasStar = fasStar;
  farStar = farStar;
  faEnvelope = faEnvelope;


  today: any;
  startDate: any;
  endDate: any;
  diffStartToday: boolean;
  diffEndToday: boolean;
  diffStartEnd: number;
  actual: boolean; //startDate < today && endDate > today
  expired: boolean; //startDate< today && endDate < today
  future: boolean; //startDate >today && endDate > today




  text:any = {
    Year: 'Év',
    Month: 'Hónap',
    Weeks: "Hét",
    Days: "nap",
    Hours: "óra",
    Minutes: "perc",
    Seconds: "másodperc",
    MilliSeconds: "MilliSeconds"
  };



  public latitude: number;
  public longitude: number;
  public zoom: number = 15;
  public map: google.maps.Marker;
  public userName: string;


  constructor(
    private route: ActivatedRoute,
    private propertyAdvertService: PropertyService,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone,
    private router: Router,
    private userService: UserService,
    private messagingService: MessagingService,
    private bidService: BidService,
    private datePipe: DatePipe

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
          this.getLastBid();

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
      (data: PropertyAdvertDetailsModel) => {
        this.propertyAdvertDetails = data;
        this.propertyAdvertDetails.startOfAuction = data.startOfAuction;
        this.propertyAdvertDetails.endOfAuction = data.endOfAuction;
        this.startDate = this.datePipe.transform(this.propertyAdvertDetails?.startOfAuction, 'medium')
        this.endDate = this.datePipe.transform(this.propertyAdvertDetails?.endOfAuction, 'medium')
        this.today = this.datePipe.transform(Date.now(),'medium');

        this.diffStartToday = new Date(this.startDate).getTime() <= new Date(this.today).getTime();
        this.diffEndToday = new Date(this.endDate).getTime() >= new Date(this.today).getTime();

        this.diffStartEnd = Math.floor(Math.abs((new Date(this.endDate).getTime()-new Date(this.startDate).getTime())/ (1000*60*60*24)));

        this.actual = (this.diffStartToday === true) && (this.diffEndToday === true);
        this.expired = (this.diffEndToday === false);
        this.future = (this.diffStartToday === false);

          //actual: boolean; //startDate < today && endDate > today
          //expired: boolean; //startDate< today && endDate < today
          //future: boolean; //startDate >today && endDate > today


        console.log(this.startDate);
        console.log(this.endDate);
        console.log(this.today);
        console.log("Start: " + this.diffStartToday);
        console.log("End: " + this.diffEndToday);
        console.log("Future: " + this.future);
        console.log("Actual: " + this.actual);
        console.log("Expired: " + this.expired);
        console.log(data);
      },

      error => console.warn(error),

    );
  }



  getLastBid() {
    this.bidService.getLastBid(Number(this.id)).subscribe(
      lastAmount => {
        this.lastBidAmount = lastAmount ;
        this.propertyAdvertDetails.actualPrice = this.lastBidAmount;
        this.nextBid = (this.propertyAdvertDetails?.actualPrice +0.1).toFixed(1);
      }
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
