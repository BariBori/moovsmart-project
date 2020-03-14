import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { PropertyFormComponent } from './components/property-form/property-form.component';
import { PropertyDetailsComponent } from './components/property-details/property-details.component';
import { PropertyListComponent } from './components/property-list/property-list.component';
import { UserregisterFormComponent } from './components/userregister-form/userregister-form.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { HttpInterceptorService } from './services/http-interceptor.service';
import { SearchComponent } from './components/search/search.component';
import { UserHomeComponent } from './components/user-home/user-home.component';
import {AgmCoreModule} from "@agm/core";
import { NgxPopper } from 'angular-popper';
import {MatSort, MatSortModule} from "@angular/material/sort";
import {MatTableModule} from "@angular/material/table";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";


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
    SearchComponent,
    UserHomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSortModule,
    MatTableModule,
    BrowserAnimationsModule,
    FontAwesomeModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBieURECuG2MJeyW0-wDI6itDhOTKFGS0w',
      libraries: ['places']
    }),
    NgxPopper,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
