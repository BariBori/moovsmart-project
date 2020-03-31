import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { UserHomeComponent } from "./user-home/user-home.component";

import { UserPropertyComponent } from './user-property/user-property.component';
import { UserDetailsComponent } from "./user-details/user-details.component";

import { UserRoutingModule } from "./user-routing.module";
import { MatSidenavModule } from "@angular/material/sidenav";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faStar, faUser } from "@fortawesome/free-regular-svg-icons";
import { faCity, faEnvelope } from "@fortawesome/free-solid-svg-icons";
import { MatBadgeModule } from '@angular/material/badge';

@NgModule({
  imports: [
    CommonModule,
    UserRoutingModule,
    MatSidenavModule,
    FontAwesomeModule,
    MatBadgeModule
  ],
  declarations: [
    UserHomeComponent,
    UserDetailsComponent,
    UserPropertyComponent,
  ]
}

)
export class UserModule {

  constructor() {
    library.add(faStar, faUser, faCity, faEnvelope);
  }
}
