import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserregisterFormComponent } from './userregister-form.component';

describe('UserregisterFormComponent', () => {
  let component: UserregisterFormComponent;
  let fixture: ComponentFixture<UserregisterFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserregisterFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserregisterFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
