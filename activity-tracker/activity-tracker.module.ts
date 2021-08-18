import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '@shared';
import { ActivityTrackerComponent } from './activity-tracker.component';
import { DemoComponent } from './components/demo/demo.component';
import { ActivityTrackerRoutingModule } from './activity-tracker-routing.module';


@NgModule({
  declarations: [ActivityTrackerComponent, DemoComponent],
  imports: [
    CommonModule,
    SharedModule,
    ActivityTrackerRoutingModule
  ]
})
export class ActivityTrackerModule { }
