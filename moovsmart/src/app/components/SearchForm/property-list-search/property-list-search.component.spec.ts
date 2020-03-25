import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertyListSearchComponent } from './property-list-search.component';

describe('PropertyListSearchComponent', () => {
  let component: PropertyListSearchComponent;
  let fixture: ComponentFixture<PropertyListSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PropertyListSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertyListSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
