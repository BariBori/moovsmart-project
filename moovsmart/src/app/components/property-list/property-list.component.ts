import {Component, OnInit} from '@angular/core';
import {PropertyService} from "../../services/property.service";
import {Router} from "@angular/router";
import {PropertyListItemModel} from "../../models/propertyListItem.model";

@Component({
  selector: 'app-property-list',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})
export class PropertyListComponent implements OnInit {

  propertyListItemModels: Array<PropertyListItemModel>;

  constructor(private propertyService: PropertyService,
              private router: Router) {
  }

  ngOnInit() {
    this.propertyService.getPropertyList().subscribe(
      propertyListItems => this.propertyListItemModels = propertyListItems
    );
  }

  details(id: number) {
    this.router.navigate(['property-details', id]);
  }

  archivePropertyAdvert(id: number) {
    this.propertyService.archivePropertyAdvert(id).subscribe(
      (response: PropertyListItemModel[]) => {
        this.propertyListItemModels = response;
      },
      error => console.warn(error),
    );
  }

}
