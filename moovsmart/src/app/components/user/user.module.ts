import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import {UserHomeComponent} from "./user-home/user-home.component";

import { UserPropertyComponent } from './user-property/user-property.component';
import {UserDetailsComponent} from "./user-details/user-details.component";

import {UserRoutingModule} from "./user-routing.module";
import {MatSidenavModule} from "@angular/material/sidenav";

@NgModule({
  imports: [
    CommonModule,
    UserRoutingModule,
    MatSidenavModule
  ],
  declarations: [
    UserHomeComponent,
    UserDetailsComponent,
    UserPropertyComponent,
  ]
  }

)
export class UserModule{}
