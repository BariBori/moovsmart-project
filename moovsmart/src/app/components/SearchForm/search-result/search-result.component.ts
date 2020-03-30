import {ChangeDetectorRef, Component, Input, OnChanges, OnInit, ViewChild} from '@angular/core';
import {PropertyService} from "../../../services/property.service";
import {PropertyListItemModel} from "../../../models/propertyListItem.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {Router} from "@angular/router";


@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {


  displayedColumns: string[] = ['image', 'address','numberOfRooms', 'area', 'price', 'price/area', 'advertId'];
  dataSource: MatTableDataSource<PropertyListItemModel>;


  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(
              //private ref: ChangeDetectorRef,
              private propertyService: PropertyService,
              private router: Router
  ) { }

  ngOnInit(): void {
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


}
