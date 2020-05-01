import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {BidService} from "../../services/bid.service";
import {PropertyAdvertDetailsModel} from "../../models/propertyAdvertDetails.model";
import {User} from "../../models/error/User";
import DateTimeFormat = Intl.DateTimeFormat;



@Component({
  selector: 'app-bid-form',
  templateUrl: './bid-form.component.html',
  styleUrls: ['./bid-form.component.css']
})
export class BidFormComponent implements OnInit {

  advertId: number;
  userId: number;

  constructor(
    private formBuilder: FormBuilder,
    private bidService: BidService,
    private router: Router,
    private userService: UserService,
    private route: ActivatedRoute
  ) { }

  bidForm = this.formBuilder.group({
    amountOfBid : [null],

  })

  ngOnInit(): void {
      this.advertId = Number(this.route.snapshot.paramMap.get('id'));
      console.log(this.advertId);

      this.userService.getCurrentUser.subscribe(
        user => this.userId = user.id
      );

      console.log(this.userId);

  };

  submit(bidFormDataModel){

    this.bidService.createBid(bidFormDataModel, this.advertId);
    console.log("bid created");
  }

}
