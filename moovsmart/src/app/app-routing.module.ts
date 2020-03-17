import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PropertyListComponent} from './components/property-list/property-list.component';
import {PropertyFormComponent} from './components/property-form/property-form.component';
import {PropertyDetailsComponent} from './components/property-details/property-details.component';
import {UserregisterFormComponent} from './components/userregister-form/userregister-form.component';
import {UserLoginComponent} from './components/user-login/user-login.component';
import {HomeComponent} from './components/home/home.component';
import {UserHomeComponent} from './components/user-home/user-home.component';

const routes: Routes = [
  {path: '', component: PropertyListComponent},
  {path: 'property-list', component: PropertyListComponent},
  {path: 'property-form', component: PropertyFormComponent},
  {path: 'property-form/:id', component: PropertyFormComponent},
  {path: 'property-details/:id', component: PropertyDetailsComponent},
  {path: 'userregister-form', component: UserregisterFormComponent},
  {path: 'user-login', component: UserLoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'user-home', component: UserHomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
