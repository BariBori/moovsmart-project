import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  constructor(private service: UserService) { }

  ngOnInit(): void {
    this.service.getCredentials().subscribe(
      console.log,
      console.error
    )
  }

}
