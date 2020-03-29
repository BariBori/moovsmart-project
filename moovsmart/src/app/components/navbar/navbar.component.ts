import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { UserService } from "../../services/user.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  userName: string;
  isLoggedUser: boolean;

  constructor(
    private userService: UserService,
    private router: Router
  ) {

  }

  ngOnInit() {
    this.userService.loggedIn.subscribe(
      isLoggedIn => this.isLoggedUser = isLoggedIn
    );
  }


  onSubmit() {
    this.router.navigate(
      this.userService.isLoggedIn()
        ? ['property-form']
        : ['user-login']
    );
  }

  onLogOut() {
    this.userService.logOut.subscribe(
      () => this.router.navigate(['home'])
    );
  }




}
