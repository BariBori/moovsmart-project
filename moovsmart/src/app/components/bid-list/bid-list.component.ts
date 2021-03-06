import {Component, OnInit} from '@angular/core';
import {BidService} from '../../services/bid.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BidListItemModel} from '../../models/bids/bidListItem.model';
import {UserService} from '../../services/user.service';
import {PropertyService} from '../../services/property.service';
import {PropertyAdvertDetailsModel} from '../../models/propertyAdvertDetails.model';

@Component({
  selector: 'app-bid-list',
  templateUrl: './bid-list.component.html',
  styleUrls: ['./bid-list.component.css']
})
export class BidListComponent implements OnInit {

  datasource: Array<BidListItemModel>;
  user: String;
  advertId: number;
  numberOfBidUsers: number;
  propertyAdvert: PropertyAdvertDetailsModel;
  startOfAuction: any;
  endOfAuction: any;

  constructor(
    private bidService: BidService,
    private propertyService: PropertyService,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    const advertId = Number(this.route.snapshot.paramMap.get('id'));
    this.bidService.getBidList(advertId).subscribe(
      bidListItems => {
        this.datasource = bidListItems;
      });
    this.bidService.getNumberOfBidUsers(advertId).subscribe(
      numberOfBidders => {
        this.numberOfBidUsers = numberOfBidders;
      }
    );
    this.propertyService.fetchAdvertDetails(String(advertId)).subscribe(
      propertyDetails => {
        this.propertyAdvert = propertyDetails;
        this.startOfAuction = this.propertyAdvert.startOfAuction;
        this.endOfAuction = this.propertyAdvert.endOfAuction;
      });
    }

}


