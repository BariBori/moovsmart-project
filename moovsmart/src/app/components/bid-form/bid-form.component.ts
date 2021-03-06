import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../services/user.service';
import {BidService} from '../../services/bid.service';
import {BidFormDataModel} from '../../models/bids/bidFormData.model';
import {PropertyService} from '../../services/property.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {MatDialogRef} from '@angular/material/dialog';
import {validationHandler} from '../../utils/validationHandler';
import {PropertyAdvertDetailsModel} from '../../models/propertyAdvertDetails.model';

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
  nextBidNumber: number;
  property: PropertyAdvertDetailsModel;

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
  });

  ngOnInit(): void {
    this.advertId = Number(this.route.snapshot.paramMap.get('id'));
    this.userService.getCurrentUser.subscribe(
      user => this.userId = user.id
    );
    this.getLastBid();

  }


  onSubmit() {
    const formData: BidFormDataModel = this.bidForm.value;
    this.bidService.createBid(formData, this.advertId).subscribe(
      () => {
        this.router.navigate(['../property-details/' + this.advertId]);
        this.bidForm.reset();
        location.reload();
    },
      error => {
        if (error.status === 500) {
          alert('Már beérkezett ilyen összegű licit, próbálkozz magasabb összeggel!');
          location.reload();
        }
        validationHandler(error, this.bidForm);
      }
    );

  }

  openDialog(content, logging) {
    if (this.userService.isLoggedIn()) {
      this.modalService.open(content, {centered: true});
    } else {
      this.modalService.open(logging, {centered: true});
    }
  }

  loggingOn() {
    this.router.navigate(['user-login']);
  }

  getLastBid() {
    this.bidService.getLastBid(this.advertId).subscribe(
      lastAmount => {
        this.lastBidAmount = lastAmount ;
        if (this.lastBidAmount != null) {
          this.nextBid = (this.lastBidAmount + 0.1).toFixed(1);
          this.nextBidNumber = Number((this.lastBidAmount + 0.1).toFixed(1));
          console.log(this.nextBidNumber);
        } else {
          this.propertyService.fetchAdvertDetails(String(this.advertId)).subscribe(
            property => {
              this.property = property;
              this.nextBid = (this.property.actualPrice + 0.1).toFixed(1);
              this.nextBidNumber = Number((this.property.actualPrice + 0.1).toFixed(1));
              console.log(this.nextBidNumber);
            }
          );
        }
      }
    );
  }



}
