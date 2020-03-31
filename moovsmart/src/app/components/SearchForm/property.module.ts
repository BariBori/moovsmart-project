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
import {MatSort, MatSortModule} from "@angular/material/sort";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatNativeDateModule, MatRippleModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatTooltipModule} from "@angular/material/tooltip";
import {CdkTableModule} from "@angular/cdk/table";


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    PropertyRoutingModule,
    CdkTableModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatRippleModule,
    MatSelectModule,
    MatSortModule,
    MatTableModule,
    MatTooltipModule,],

  declarations: [
    SearchComponent,
    SearchResultComponent,
    PropertyListSearchComponent,
    FilterPipe],

  providers: [SharingSearchService]

})

export class PropertyModule{}
