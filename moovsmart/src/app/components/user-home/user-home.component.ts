import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/error/User';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  id: number;
  constructor(private service: UserService) { }
  user: User;
  ngOnInit(): void {
    this.id = this.service.getId();
    this.service.getCurrentUser.subscribe(
      gotUser => this.user = gotUser,
      console.error
    );
  }

}
