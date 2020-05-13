import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PropertyListComponent } from './components/property-list/property-list.component';
import { PropertyFormComponent } from './components/property-form/property-form.component';
import { PropertyDetailsComponent } from './components/property-details/property-details.component';
import { UserregisterFormComponent } from './components/userregister-form/userregister-form.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { HomeComponent } from './components/home/home.component';
import { UserHomeComponent } from './components/user/user-home/user-home.component';
import { SelectivePreloadingStrategyService } from './selective-preloading-strategy.service';
import { MessagingComponent } from './components/messaging/messaging.component';
import {PropertyListBsComponent} from './components/property-list-bs/property-list-bs.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'property-list', component: PropertyListComponent },
  { path: 'property-list-bs', component: PropertyListBsComponent},
  { path: 'property-form', component: PropertyFormComponent },
  { path: 'property-form/:id', component: PropertyFormComponent },
  { path: 'property-details/:id', component: PropertyDetailsComponent },
  { path: 'userregister-form', component: UserregisterFormComponent },
  { path: 'user-login', component: UserLoginComponent },
  { path: 'messaging', component: MessagingComponent },
  { path: 'home', component: HomeComponent },
  {
    path: 'user-home',
    loadChildren: () => import('./components/user/user.module').then(m => m.UserModule),
  },
  {
    path: 'property-list-search',
    loadChildren: () => import('./components/SearchForm/property.module').then(m => m.PropertyModule),
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes,
    {
      enableTracing: false,
      preloadingStrategy: SelectivePreloadingStrategyService,
    }
  )

  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
