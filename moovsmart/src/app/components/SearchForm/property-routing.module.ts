import {PropertyListSearchComponent} from "./property-list-search/property-list-search.component";
import {RouterModule, Routes} from "@angular/router";
import {SearchComponent} from "./search/search.component";
import {SearchResultComponent} from "./search-result/search-result.component";
import {NgModule} from "@angular/core";


const propertyRoutes: Routes = [
  {
    path: '',
    component: PropertyListSearchComponent,
  children:[
    {
    path: '',
    children:[
      {path: 'search', component: SearchComponent},
      {path: 'search-result', component: SearchResultComponent}
    ]
  }
  ]
  }
  ];

@NgModule({
  imports: [
    RouterModule.forChild(propertyRoutes)
  ],
  exports: [
    RouterModule
  ]
})

export class PropertyRoutingModule{}
