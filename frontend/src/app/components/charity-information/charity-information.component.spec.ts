import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CharityInformationComponent } from './charity-information.component';

describe('CharityInformationComponent', () => {
  let component: CharityInformationComponent;
  let fixture: ComponentFixture<CharityInformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CharityInformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CharityInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
