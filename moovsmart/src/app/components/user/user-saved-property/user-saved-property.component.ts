import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from "@angular/material/table";
import { PropertyListItemModel } from "../../../models/propertyListItem.model";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { PropertyService } from "../../../services/property.service";
import { UserService } from "../../../services/user.service";
import { Router } from "@angular/router";
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-user-saved-property',
  templateUrl: './user-saved-property.component.html',
  styleUrls: ['./user-saved-property.component.css']
})
export class UserSavedPropertyComponent implements OnInit {

  displayedColumns: string[] = ['image', 'address', 'numberOfRooms', 'area', 'price', 'priceForSquareMeter', 'advertId'];
  dataSource: MatTableDataSource<PropertyListItemModel>;
  data: Observable<MatTableDataSource<PropertyListItemModel>>;

  userName: string;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private propertyService: PropertyService,
    private router: Router
  ) {
    this.data = propertyService.savedAdverts.pipe(
      map(ads => new MatTableDataSource(ads))
    );
  }

  applyFilter(filterEvent: any) {
    filterEvent = filterEvent.target.value.trim();
    filterEvent = filterEvent.toLowerCase();
    this.dataSource.filter = filterEvent;


    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngOnInit(): void {
    //TODO
  }

  goToDetails(id: number) {
    this.router.navigate(['property-details', id]);
  }

  deleteAdvert() {

  }
}
