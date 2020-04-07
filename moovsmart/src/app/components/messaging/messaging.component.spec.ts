import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessagingComponent } from './messaging.component';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from 'src/app/services/user.service';
import { MessagingService } from 'src/app/services/messaging.service';
import { NotificationService } from 'src/app/services/notification.service';
import { RouterTestingModule } from '@angular/router/testing';
import { UserHomeComponent } from '../user/user-home/user-home.component';

describe('MessagingComponent', () => {
  let component: MessagingComponent;
  let fixture: ComponentFixture<MessagingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule,
        RouterTestingModule.withRoutes([
          { path: 'user-login', component: UserHomeComponent }
        ])
      ],
      providers: [
        UserService,
        MessagingService,
        NotificationService,
      ],
      declarations: [MessagingComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessagingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
