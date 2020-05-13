import { Component, OnInit } from '@angular/core';
import {BidService} from "../../../services/bid.service";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";
import {MyBidsModel} from "../../../models/bids/myBids.model";
import {faGavel} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-user-bids',
  templateUrl: './user-bids.component.html',
  styleUrls: ['./user-bids.component.css']
})
export class UserBidsComponent implements OnInit {

  dataSource: Array<MyBidsModel>;
  faGavel = faGavel;

  constructor(private bidService: BidService,
              private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.userService.getCurrentUser.subscribe(
      gotUser => {
        this.bidService.getMyBids(gotUser.userName).subscribe(
          myBids =>{
            this.dataSource = myBids;
            console.log(this.dataSource);
          }
        )
      }
    )
  }

  goToDetails(id: number) {
    this.router.navigate(['property-details', id]);
  }

}
