import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { UserService } from "../../services/user.service";
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  userName: string;



  constructor(
    private userService: UserService,
    private router: Router
  ) {

  }

  ngOnInit() {
  }

  onSubmit() {
    this.router.navigate(
      this.userService.isLoggedIn()
        ? ['property-form']
        : ['user-login']
    );
  }


}
