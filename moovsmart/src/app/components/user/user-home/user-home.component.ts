import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/error/User';
import {faCity, faEnvelope, faUser} from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  faCity = faCity;
  faEnvelope = faEnvelope;
  faUser = faUser;

  constructor(private service: UserService) { }

  user: User;
  ngOnInit(): void {
    console.log(this.service.isLoggedIn());
    this.service.getCurrentUser.subscribe(
      gotUser => this.user = gotUser,
      console.error
    );
  }

}
