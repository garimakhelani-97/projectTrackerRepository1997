import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ActivityTrackerComponent } from './activity-tracker.component';
import { DemoComponent } from './components/demo/demo.component';


const routes: Routes = [
    {
        path: '',
        component: ActivityTrackerComponent,
        children: [
            { path: '', component: DemoComponent },
        ],
    },
];


@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class ActivityTrackerRoutingModule { }
