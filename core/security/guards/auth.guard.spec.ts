import { TestBed } from '@angular/core/testing';

import { ScreenGuard } from './auth.guard';

describe('AuthGuard', () => {
  let guard: ScreenGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(ScreenGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
