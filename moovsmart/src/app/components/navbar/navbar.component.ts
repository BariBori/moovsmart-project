import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  userName: string;


  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.userService.getCurrentUser.subscribe(
      data => this.userName = data.userName
    );
  }

  onSubmit(){
    if(this.userName == null) {
      this.router.navigate(['user-login']);
    } else {
      this.router.navigate(['property-form']);
    }
  }

}
