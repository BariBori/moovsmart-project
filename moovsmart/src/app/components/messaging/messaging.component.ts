import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MessagingService } from 'src/app/services/messaging.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-messaging',
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.css']
})
export class MessagingComponent implements OnInit {

  message: FormControl;

  constructor(
    private msgservice: MessagingService,
    usrService: UserService
  ) {
    this.message = new FormControl('', Validators.required);
  }
  send() {
  }

  ngOnInit(): void {
    this.msgservice.fetcchAllTopics.subscribe(console.log, console.error)
  }

}
