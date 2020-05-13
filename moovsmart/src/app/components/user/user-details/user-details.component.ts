import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../services/user.service';
import {User} from '../../../models/error/User';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  constructor(private service: UserService) { }

  user: User;
  ngOnInit(): void {
    this.service.getCurrentUser.subscribe(
      gotUser => this.user = gotUser,
      console.error
    );
  }

}
