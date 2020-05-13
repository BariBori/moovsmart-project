import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {PropertyListItemModel} from '../../../models/propertyListItem.model';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort, MatSortable} from '@angular/material/sort';
import {PropertyService} from '../../../services/property.service';
import {Router} from '@angular/router';
import {UserService} from '../../../services/user.service';

@Component({
  selector: 'app-user-property',
  templateUrl: './user-property.component.html',
  styleUrls: ['./user-property.component.css']
})
export class UserPropertyComponent implements OnInit {

  displayedColumns: string[] = ['advertStatus', 'address', 'price', 'advertId', 'createdAt', 'timeOfActivation'];
  dataSource: MatTableDataSource<PropertyListItemModel>;

  userName: string;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private propertyService: PropertyService,
              private userService: UserService,
              private router: Router) { }



  ngOnInit(): void {
    this.userService.getCurrentUser.subscribe(
      gotUser => {
        console.log(gotUser.userName);
        this.propertyService.getMyProperties(gotUser.userName).subscribe(
          propertyListItems => {
            this.dataSource = new MatTableDataSource(propertyListItems);
            this.dataSource.paginator = this.paginator;
            this.sort.sort(({ id: 'createdAt', start: 'desc'}) as MatSortable);
            this.dataSource.sort = this.sort;
            console.log(this.dataSource);
          });
      }
    );
  }

  goToDetails(id: number) {
    this.router.navigate(['property-details', id]);
  }

}
