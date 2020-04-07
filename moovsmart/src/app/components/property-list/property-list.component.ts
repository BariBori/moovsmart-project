import {Component, inject, InjectionToken, OnInit, ViewChild} from '@angular/core';
import {PropertyService} from "../../services/property.service";
import {Router} from "@angular/router";
import {PropertyListItemModel} from "../../models/propertyListItem.model";
import {MatSort, Sort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-property-list',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})
export class PropertyListComponent implements OnInit {

  displayedColumns: string[] = ['image', 'address','numberOfRooms', 'area', 'price', 'priceForSquareMeter', 'advertId'];
  dataSource: MatTableDataSource<PropertyListItemModel>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private propertyService: PropertyService,
              private router: Router) {
  }


  applyFilter(filterEvent: any) {
    filterEvent = filterEvent.target.value.trim();
    filterEvent = filterEvent.toLowerCase();
    this.dataSource.filter = filterEvent;

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }

  }

  ngOnInit() {
    this.propertyService.getPropertyList().subscribe(
      propertyListItems => {
        this.dataSource = new MatTableDataSource(propertyListItems);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        console.log(this.dataSource);
      });
  }


  goToDetails(id: number) {
    this.router.navigate(['property-details', id]);
  }

  changePage(pageIndex: number, pageSize: number) {
    this.propertyService.getFilteredPropertyList(pageIndex, pageSize).subscribe(
      propertyListItems => {
        this.dataSource = new MatTableDataSource(propertyListItems);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        console.log(this.dataSource);
      }
    )
  }
}
