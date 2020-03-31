import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/error/User';
import { faCity, faEnvelope, faUser, faCircle } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { MatBadgeModule } from '@angular/material/badge';


@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  faCity = faCity;
  faEnvelope = faEnvelope;
  faUser = faUser;
  faCircle = faCircle;

  constructor(
    private service: UserService,
    private router: Router) { }

  user: User;
  ngOnInit(): void {
    this.service.isLoggedIn()
      ?
      this.service.getCurrentUser.subscribe(
        gotUser => this.user = gotUser,
      )
      : this.router.navigate(['user-login']);
  }

}
