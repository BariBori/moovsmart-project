import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchResultMapComponent } from './search-result-map.component';

describe('SearchResultMapComponent', () => {
  let component: SearchResultMapComponent;
  let fixture: ComponentFixture<SearchResultMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SearchResultMapComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchResultMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  xit('should create', () => {
    expect(component).toBeTruthy();
  });
});
