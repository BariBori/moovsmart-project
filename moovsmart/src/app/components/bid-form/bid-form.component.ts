import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {BidService} from "../../services/bid.service";
import {BidFormDataModel} from "../../models/bids/bidFormData.model";
import {PropertyService} from "../../services/property.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-bid-form',
  templateUrl: './bid-form.component.html',
  styleUrls: ['./bid-form.component.css']
})
export class BidFormComponent implements OnInit {

  advertId: number;
  userId: number;
  amountOfBid: number;
  lastBidAmount: number;
  nextBid: string;

  constructor(
    private formBuilder: FormBuilder,
    private bidService: BidService,
    private router: Router,
    private userService: UserService,
    private route: ActivatedRoute,
    private propertyService: PropertyService,
    private modalService: NgbModal,
  ) {
  }

  bidForm = this.formBuilder.group({
    amountOfBid: ['']
  })

  ngOnInit(): void {
    this.advertId = Number(this.route.snapshot.paramMap.get('id'));
    this.userService.getCurrentUser.subscribe(
      user => this.userId = user.id
    );
    this.getLastBid();
  };


  onSubmit() {

    if (this.userService.isLoggedIn()) {

      let formData: BidFormDataModel = this.bidForm.value;
      this.bidService.createBid(formData, this.advertId).subscribe(
        () => {
          this.router.navigate(['../property-details/' + this.advertId])
        },
      );
    } else {
      this.router.navigate(['user-login']);
    }
    this.bidForm.reset();
    alert("Sikeres licit!");
  }

  openDialog(content) {
    this.modalService.open(content, {centered: true});
  }

  getLastBid() {
    this.bidService.getLastBid(this.advertId).subscribe(
      lastAmount => {
        this.lastBidAmount = lastAmount;
        this.nextBid = (this.lastBidAmount + 0.1).toFixed(1);

      }
    );
  }

  clearBidInput() {

  }

}
