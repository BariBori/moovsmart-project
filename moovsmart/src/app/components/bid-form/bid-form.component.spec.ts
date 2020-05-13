import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BidFormComponent } from './bid-form.component';

describe('BidFormComponent', () => {
  let component: BidFormComponent;
  let fixture: ComponentFixture<BidFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BidFormComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BidFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  xit('should create', () => {
    expect(component).toBeTruthy();
  });
});
