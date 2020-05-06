import {Component, OnInit} from '@angular/core';
import {BidService} from "../../services/bid.service";
import {ActivatedRoute, Router} from "@angular/router";
import {BidListItemModel} from "../../models/bids/bidListItem.model";
import {UserService} from "../../services/user.service";
import {PropertyService} from "../../services/property.service";

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

  constructor(
    private bidService: BidService,
    private propertyService: PropertyService,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    let advertId = Number(this.route.snapshot.paramMap.get('id'));
    console.log("List advert id:" + this.advertId);

    this.bidService.getBidList(advertId).subscribe(
      bidListItems => {
        this.datasource = bidListItems;
        console.log(this.datasource);
      }
    );
    this.getBidUserNumber(advertId);

  }

  getBidUserNumber(advertId: number) {
    this.bidService.getNumberOfBidUsers(advertId).subscribe(
      numberOfBidders => {
        this.numberOfBidUsers = numberOfBidders;
        console.log(this.numberOfBidUsers);
      }
  );

  }

}


