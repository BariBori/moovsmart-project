import { Component, OnInit } from '@angular/core';
import {faCity, faFileContract, faFileUpload, faHandshake, faSearchLocation} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  faFileContract = faFileContract;
  faFileUpload = faFileUpload;
  faSearchLocation = faSearchLocation;
  faCity = faCity;
  faDeal = faHandshake;

  constructor() { }

  ngOnInit(): void {
  }

}
