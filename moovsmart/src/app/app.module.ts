import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PropertyFormComponent } from './components/property-form/property-form.component';
import { PropertyDetailsComponent } from './components/property-details/property-details.component';
import { PropertyListComponent } from './components/property-list/property-list.component';
import { UserregisterFormComponent } from './components/userregister-form/userregister-form.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { HttpInterceptorService } from './services/http-interceptor.service';

import { AgmCoreModule } from "@agm/core";
import { FileUploadModule } from "ng2-file-upload";
import { CloudinaryModule } from "@cloudinary/angular-5.x";
import * as  Cloudinary from 'cloudinary-core';
import { NgxPopper } from 'angular-popper';
import { MatSort, MatSortModule } from "@angular/material/sort";
import { MatTableModule } from "@angular/material/table";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { library } from '@fortawesome/fontawesome-svg-core';
import {faCircle, faCity, faFileContract, faHandshake, faPaperPlane} from '@fortawesome/free-solid-svg-icons';
import { faStar } from '@fortawesome/free-regular-svg-icons';
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { MatPaginatorIntl, MatPaginatorModule } from "@angular/material/paginator";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { UserService } from './services/user.service';
import { MessagingComponent } from './components/messaging/messaging.component';
import { MatButtonModule } from "@angular/material/button";
import { MatNativeDateModule, MatRippleModule } from "@angular/material/core";
import { MatSelectModule } from "@angular/material/select";
import { MatTooltipModule } from "@angular/material/tooltip";
import { CdkTableModule } from "@angular/cdk/table";
import { MatBadgeModule } from '@angular/material/badge';




@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    PropertyFormComponent,
    PropertyDetailsComponent,
    PropertyListComponent,
    UserregisterFormComponent,
    HomeComponent,
    FooterComponent,
    UserregisterFormComponent,
    UserLoginComponent,
    MessagingComponent,

  ],
  exports: [
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgxPopper,
    FontAwesomeModule,
    FileUploadModule,
    CloudinaryModule.forRoot(Cloudinary, { cloud_name: 'dqmt1lieq', upload_preset: 's1jujbuu' }),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBieURECuG2MJeyW0-wDI6itDhOTKFGS0w',
      libraries: ['places']
    }),
    NgbModule,
    CdkTableModule,
    MatBadgeModule,
    MatTooltipModule,
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

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor() {
    library.add(faStar, faHandshake, faFileContract, faCity, faPaperPlane, faCircle);
  }
}

