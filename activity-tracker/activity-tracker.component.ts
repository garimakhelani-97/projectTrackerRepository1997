import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-activity-tracker',
  template: `<breadcrumb></breadcrumb><router-outlet></router-outlet>`,
})
export class ActivityTrackerComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log("phat ja")
  }

}
