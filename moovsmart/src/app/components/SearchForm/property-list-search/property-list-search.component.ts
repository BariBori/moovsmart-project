import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-property-list-search',
  templateUrl: './property-list-search.component.html',
  styleUrls: ['./property-list-search.component.css']
})
export class PropertyListSearchComponent {

  searchText: string;
  filters: Object;

  constructor() { }

  ngOnInit(): void {
  }

}
