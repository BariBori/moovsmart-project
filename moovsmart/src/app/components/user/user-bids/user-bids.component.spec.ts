import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserBidsComponent } from './user-bids.component';

describe('UserBidsComponent', () => {
  let component: UserBidsComponent;
  let fixture: ComponentFixture<UserBidsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserBidsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserBidsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
