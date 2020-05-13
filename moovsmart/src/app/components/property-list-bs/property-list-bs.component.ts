import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {PropertyService} from '../../services/property.service';
import {Router} from '@angular/router';
import {PropertyListItemModel} from '../../models/propertyListItem.model';
import {faCameraRetro} from '@fortawesome/free-solid-svg-icons/faCameraRetro';

@Component({
  selector: 'app-property-list-bs',
  templateUrl: './property-list-bs.component.html',
  styleUrls: ['./property-list-bs.component.css']
})
export class PropertyListBsComponent implements OnInit {

  faCameraRetro = faCameraRetro;

  page = 1;
  pageSize = 4;
  dataSource: any;
  collectionSize: number;



  constructor(private propertyService: PropertyService,
              private router: Router) { }

  ngOnInit(): void {

    this.propertyService.getPropertyList().subscribe(
      propertyList => {
        this.dataSource = propertyList;
        console.log(this.dataSource);
        this.collectionSize = this.dataSource.length;
        console.log(this.collectionSize);
      }
    );
  }

  goToDetails(id: number) {
    this.router.navigate(['property-details', id]);
  }

}
