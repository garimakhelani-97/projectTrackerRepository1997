import { TestBed } from '@angular/core/testing';

import { GporService } from './gpor.service';

describe('GporService', () => {
  let service: GporService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GporService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
