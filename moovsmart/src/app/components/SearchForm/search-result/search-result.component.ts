import {ChangeDetectorRef, Component, Input, OnChanges, OnInit} from '@angular/core';
import {PropertyService} from "../../../services/property.service";
import {PropertyListItemModel} from "../../../models/propertyListItem.model";


@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnChanges, OnInit {
  @Input() groupFilters: Object;
  @Input() searchByKeyword: string;

  properties: Array<PropertyListItemModel>;
  filteredProperties: any[] = [];

  constructor(
              private ref: ChangeDetectorRef,
              private propertyService: PropertyService) { }

  ngOnInit(): void {
    this.propertyService.getPropertyList().subscribe(
      properties => this.properties = properties);
    console.log(this.properties);

    this.filteredProperties = this.filteredProperties.length >0 ? this.filteredProperties : this.properties;
  console.log(this.filteredProperties);
  }

  ngOnChanges(): void {
    if(this.groupFilters) this.filterPropertiesList(this.groupFilters, this.properties);
  }

  filterPropertiesList(filters: any, properties: any): void{
    this.filteredProperties = this.properties;
    const keys = Object.keys(filters);
    const filterProperty = property => {
      let result = keys.map(key =>{
        if(!~key.indexOf('price')){
          if(property[key]){
            return String(property[key].toLowerCase().startsWith(String(filters[key]).toLowerCase()))
          } else {
            return false;
          }
        }
      });

      result = result.filter(it => it !== undefined);

      if(filters['priceFrom'] && filters['priceTo']){
        if(property['price']){
          if(+property['price'] > +filters['priceFrom'] && +property['price'] <= +filters['priceTo']){
            result.push('true');
          } else {
            result.push(false);
          }
        } else {
          result.push(false);
        }
      }
      return result.reduce((acc, cur: any) =>{ return acc & cur}, 1)
    }
    this.filteredProperties = this.properties.filter(filterProperty);
  }

  loadProperties(): void{
    this.propertyService.getPropertyList().subscribe(
      properties => this.properties = properties);
    console.log(this.properties);

    this.filteredProperties = this.filteredProperties.length >0 ? this.filteredProperties : this.properties;
  }

}
