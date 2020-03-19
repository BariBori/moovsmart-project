import {Component, OnInit, ViewChild} from '@angular/core';
import {PropertyService} from "../../services/property.service";
import {Router} from "@angular/router";
import {PropertyListItemModel} from "../../models/propertyListItem.model";
import {Sort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-property-list',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})
export class PropertyListComponent implements OnInit {

  propertyListItemModels: Array<PropertyListItemModel>;

  sortedData: PropertyListItemModel[];

  dataSource = new MatTableDataSource<PropertyListItemModel>();

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;


  constructor(private propertyService: PropertyService,
              private router: Router) {
    this.sortedData = this.propertyListItemModels;
  }

  sortData(sort: Sort) {
    const data = this.propertyListItemModels;
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'title': return this.compare(a.title, b.title, isAsc);
        case 'numberOfRooms': return this.compare(a.numberOfRooms, b.numberOfRooms, isAsc);
        case 'area': return this.compare(a.area, b.area, isAsc);
        case 'price': return this.compare(a.price, b.price, isAsc);
        default: return 0;
      }
    });
  }

   compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  ngOnInit() {
    this.propertyService.getPropertyList().subscribe(
      propertyListItems => this.propertyListItemModels = propertyListItems);
    this.dataSource.paginator = this.paginator;
  }

  details(id: number) {
    this.router.navigate(['property-details', id]);
  }

  goToDetails(id: number) {
    this.router.navigate(['property-details', id]);
  }

  // editProperty(id: number) {
  //   this.router.navigate(['property-form',id]);
  // }
}
