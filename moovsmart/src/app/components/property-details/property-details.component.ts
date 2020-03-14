import {Component, OnInit} from '@angular/core';
import {PropertyAdvertDetailsModel} from "../../models/propertyAdvertDetails.model";
import {PropertyService} from "../../services/property.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-property-details',
  templateUrl: './property-details.component.html',
  styleUrls: ['./property-details.component.css']
})
export class PropertyDetailsComponent implements OnInit {

  advertId: number;
  propertyAdvertDetails: PropertyAdvertDetailsModel;

  constructor(private route: ActivatedRoute, private propertyAdvertService: PropertyService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      paramMap => {
        const id: number = Number(paramMap.get('id'));
        if (id) {
          this.advertId = id;
          this.loadPropertyAdvertDetails();
        }
      },
    );
  }

  loadPropertyAdvertDetails() {
    this.propertyAdvertService.fetchAdvertDetails(this.advertId).subscribe(
      (data: PropertyAdvertDetailsModel) => this.propertyAdvertDetails = data,
      error => console.warn(error),
    );
  }

}
