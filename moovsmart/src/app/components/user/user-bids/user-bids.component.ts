import { Component, OnInit } from '@angular/core';
import {BidService} from "../../../services/bid.service";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";
import {MyBidsModel} from "../../../models/bids/myBids.model";
import {faGavel, faHandPaper} from "@fortawesome/free-solid-svg-icons";
import {BidListItemModel} from "../../../models/bids/bidListItem.model";

@Component({
  selector: 'app-user-bids',
  templateUrl: './user-bids.component.html',
  styleUrls: ['./user-bids.component.css']
})
export class UserBidsComponent implements OnInit {

  dataSource: Array<MyBidsModel>;
  bidSource: Array<BidListItemModel>;
  faGavel = faGavel;
  faHandPaper = faHandPaper;

  constructor(private bidService: BidService,
              private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.userService.getCurrentUser.subscribe(
      gotUser => {
        this.bidService.getMyBidProperties(gotUser.userName).subscribe(
          myBids =>{
            this.dataSource = myBids;
            console.log(this.dataSource);
          }
        );
      }
    )

  }

  goToDetails(id: number) {
    this.router.navigate(['property-details', id]);
  }

  goToBids(id:number){
    this.bidService.getBidList(id).subscribe(
      propertyBids =>{
        this.bidSource = propertyBids;
      }
    )
  }

}
