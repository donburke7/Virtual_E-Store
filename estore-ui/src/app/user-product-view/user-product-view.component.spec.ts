import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProductViewComponent } from './user-product-view.component';

describe('UserProductViewComponent', () => {
  let component: UserProductViewComponent;
  let fixture: ComponentFixture<UserProductViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserProductViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserProductViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
