import {RouterModule, Routes} from "@angular/router";
import {UserHomeComponent} from "./user-home/user-home.component";
import {NgModule} from "@angular/core";
import {UserDetailsComponent} from "./user-details/user-details.component";
import {UserPropertyComponent} from "./user-property/user-property.component";

const userRoutes: Routes = [
  {
    path: '',
    component: UserHomeComponent,
    children: [
      {
        path: '',
        children: [
          {path: 'user-details', component: UserDetailsComponent},
          {path: 'user-property', component: UserPropertyComponent}
        ]
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(userRoutes)
  ],
  exports: [
    RouterModule
  ]
})

export class UserRoutingModule{}
