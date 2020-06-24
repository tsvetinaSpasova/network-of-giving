import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WhyToGiveCharityComponent } from './why-to-give-charity.component';

describe('WhyToGiveCharityComponent', () => {
  let component: WhyToGiveCharityComponent;
  let fixture: ComponentFixture<WhyToGiveCharityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WhyToGiveCharityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WhyToGiveCharityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
