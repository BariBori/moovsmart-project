import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
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
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";

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
    SearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FontAwesomeModule
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
