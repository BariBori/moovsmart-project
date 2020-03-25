import {Component, OnChanges, OnInit} from '@angular/core';
import { Router } from "@angular/router";
import { UserService } from "../../services/user.service";
import { tap } from 'rxjs/operators';
import {error} from "@angular/compiler/src/util";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnChanges {

  userName: string;
  isLoggedUser: boolean;

  constructor(
    private userService: UserService,
    private router: Router
  ) {

  }

  ngOnInit() {
    this.userService.getCurrentUser.subscribe(
      success => {
        this.isLoggedUser = this.userService.isLoggedIn()
      },
            error => this.isLoggedUser = this.userService.isLoggedIn()
    )
  }

  ngOnChanges(){
    this.userService.getCurrentUser.subscribe(
      success => {
        this.isLoggedUser = this.userService.isLoggedIn()
      },
      error => this.isLoggedUser = this.userService.isLoggedIn()
    )

  }

  onSubmit() {
    this.router.navigate(
      this.userService.isLoggedIn()
        ? ['property-form']
        : ['user-login']
    );
  }

  onLogOut(){
    this.userService.logOut.subscribe(
      success => this.router.navigate(['home'])
    );
  }




}
