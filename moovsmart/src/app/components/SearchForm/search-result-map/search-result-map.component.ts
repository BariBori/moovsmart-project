import { Component, OnInit } from '@angular/core';
import {PropertyService} from "../../../services/property.service";
import {SharingSearchService} from "../../../services/sharing-search.service";
import {MapsAPILoader} from "@agm/core";
import {FilterPropertyAdvertModel} from "../../../models/filterPropertyAdvert.model";
import {PropertyListItemModel} from "../../../models/propertyListItem.model";
import {PropertyAdvertDetailsModel} from "../../../models/propertyAdvertDetails.model";

@Component({
  selector: 'app-search-result-map',
  templateUrl: './search-result-map.component.html',
  styleUrls: ['./search-result-map.component.css']
})
export class SearchResultMapComponent implements OnInit {

  datasource: any;
  latitude: number;
  longitude: number;
  zoom: number;
  showMap: boolean = false;
  showPropertyDetails: boolean = false;
  propertyDetail: PropertyAdvertDetailsModel;


  constructor(
    private mapsAPILoader: MapsAPILoader,
    private propertyService: PropertyService,
    private sharingSearchService: SharingSearchService
  ) { }

  ngOnInit(): void {
    this.sharingSearchService.filteredProperties.subscribe(
      filteredProperties =>{
        console.log(filteredProperties);
        this.propertyService.postFilteredPropertyAdverts(filteredProperties).subscribe(
          propertyListItems =>{
            console.log(propertyListItems);
            this.datasource = propertyListItems;
            console.log("map-datasource");
            console.log(this.datasource);
          }
        );
      }
    );

    this.mapsAPILoader.load().then(() => {
        this.setCurrentLocation();
        this.showMap = true;
      }
    );


  }

  markerClick = (property: PropertyListItemModel) => {

    this.getPropertyDetail(String(property.id));
  }

  private getPropertyDetail = (id: string): void => {
    this.propertyService.fetchAdvertDetails(id).subscribe(
      value => this.propertyDetail = value,
      error => console.warn(error),
      ()=> {
        this.showPropertyDetails = true;
      }
    )
  }

  setCurrentLocation = () => {
      if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition(position => {
          this.latitude = position.coords.latitude;
          this.longitude = position.coords.longitude;
          this.zoom = 16;
      })
    }
  }



}
