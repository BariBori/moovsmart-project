import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SearchComponent} from "./search/search.component";
import {SearchResultComponent} from "./search-result/search-result.component";
import {PropertyListSearchComponent} from "./property-list-search/property-list-search.component";
import {FilterPipe} from "../../filter.pipe";
import {PropertyRoutingModule} from "./property-routing.module";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {SharingSearchService} from "../../services/sharing-search.service";


@NgModule({
  imports: [CommonModule, FormsModule, ReactiveFormsModule, PropertyRoutingModule, MatTableModule, MatPaginatorModule],
  declarations: [SearchComponent, SearchResultComponent, PropertyListSearchComponent, FilterPipe],
  providers: [SharingSearchService]

})

export class PropertyModule{}
