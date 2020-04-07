import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserSavedPropertyComponent } from './user-saved-property.component';

describe('UserSavedPropertyComponent', () => {
  let component: UserSavedPropertyComponent;
  let fixture: ComponentFixture<UserSavedPropertyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserSavedPropertyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserSavedPropertyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
