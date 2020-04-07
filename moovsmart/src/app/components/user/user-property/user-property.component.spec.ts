import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPropertyComponent } from './user-property.component';

describe('UserPropertyComponent', () => {
  let component: UserPropertyComponent;
  let fixture: ComponentFixture<UserPropertyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UserPropertyComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPropertyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  xit('should create', () => {
    expect(component).toBeTruthy();
  });
});
