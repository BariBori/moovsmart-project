import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SearchComponent} from "./search/search.component";
import {SearchResultComponent} from "./search-result/search-result.component";
import {PropertyListSearchComponent} from "./property-list-search/property-list-search.component";
import {FilterPipe} from "../../filter.pipe";
import {PropertyRoutingModule} from "./property-routing.module";


@NgModule({
  imports: [CommonModule, FormsModule, ReactiveFormsModule, PropertyRoutingModule],
  declarations: [SearchComponent, SearchResultComponent, PropertyListSearchComponent, FilterPipe],

})

export class PropertyModule{}
