import {Component, OnInit, ViewChild} from '@angular/core';
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

  //propertyListItemModels: Array<PropertyListItemModel> = [];
  //sortedData: PropertyListItemModel[];

  displayedColumns: string[] = ['image', 'address','numberOfRooms', 'area', 'price', 'price/area', 'advertId'];
  dataSource: MatTableDataSource<PropertyListItemModel>;


 @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private propertyService: PropertyService,
              private router: Router) {
  }

  /*ngAfterViewInit(){
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }*/

  applyFilter(filterValue:string){
    filterValue = filterValue.trim();
    //filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;

    if(this.dataSource.paginator){
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

  /*sortData(sort: Sort) {
    const data = this.propertyListItemModels;
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'address': return this.compare(a.address, b.address, isAsc);
        case 'numberOfRooms': return this.compare(a.numberOfRooms, b.numberOfRooms, isAsc);
        case 'area': return this.compare(a.area, b.area, isAsc);
        case 'price': return this.compare(a.price, b.price, isAsc);
        case 'price/area': return this.compare(a.priceForSquareMeter, b.priceForSquareMeter, isAsc);
        case 'advertId': return this.compare(a.advertId, b.advertId, isAsc);
        default: return 0;
      }
    });
  }

   compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }*/




}
