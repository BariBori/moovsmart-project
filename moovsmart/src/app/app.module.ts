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
import { UserLoginComponent } from './components/user-login/user-login.component';
import { HttpInterceptorService } from './services/http-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    PropertyFormComponent,
    PropertyDetailsComponent,
    PropertyListComponent,
    UserregisterFormComponent,
    UserLoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
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
