import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserHomeComponent } from './user-home/user-home.component';

import { UserPropertyComponent } from './user-property/user-property.component';
import { UserDetailsComponent } from './user-details/user-details.component';

import { UserRoutingModule } from './user-routing.module';
import { MatSidenavModule } from '@angular/material/sidenav';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faStar, faUser } from '@fortawesome/free-regular-svg-icons';
import { faCity, faEnvelope } from '@fortawesome/free-solid-svg-icons';
import { MatBadgeModule } from '@angular/material/badge';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgxPopper} from 'angular-popper';
import {CdkTableModule} from '@angular/cdk/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {MatSortModule} from '@angular/material/sort';
import {MatTooltipModule} from '@angular/material/tooltip';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { UserSavedPropertyComponent } from './user-saved-property/user-saved-property.component';
import {NgbButtonsModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [

      UserRoutingModule,
      CommonModule,
      FormsModule,
      ReactiveFormsModule,


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
      MatTooltipModule,
      MatSidenavModule,
      FontAwesomeModule,
      MatBadgeModule,
      NgbButtonsModule
    ],
  declarations: [
    UserHomeComponent,
    UserDetailsComponent,
    UserPropertyComponent,
    UserSavedPropertyComponent,
  ]
}

)
export class UserModule {

  constructor() {
    library.add(faStar, faUser, faCity, faEnvelope);
  }
}
