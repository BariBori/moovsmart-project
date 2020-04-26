import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertyListBsComponent } from './property-list-bs.component';

describe('PropertyListBsComponent', () => {
  let component: PropertyListBsComponent;
  let fixture: ComponentFixture<PropertyListBsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PropertyListBsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertyListBsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
