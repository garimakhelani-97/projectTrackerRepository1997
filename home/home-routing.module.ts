import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../theme/admin-layout/admin-layout.component';
import { AuthGuard } from '../core/security/guards/auth.guard';
import { HomeComponent } from './home.component';
import { ScreenGuard } from '../core/security/guards/screen.guard';
import { LandingPageResolverService } from './resolvers/landing-page-resolver.service';


const routes: Routes = [
    {
        path: 'home',
        component: AdminLayoutComponent,
        canActivate: [AuthGuard],
        children: [
            { path: '', component: HomeComponent, resolve: { landingPageData: LandingPageResolverService }},
                    {
                        path: 'dash-board',
                        loadChildren: () => import('../dashboard/dashboard.module').then(m => m.DashboardModule),
                        data: { title: 'Dashboard', titleI18n: 'dashboard' },
                        canActivate: [ScreenGuard]
                    },
                    {
                        path: 'activity-tracker',
                        loadChildren: () => import('../activity-tracker/activity-tracker.module').then(m => m.ActivityTrackerModule),
                        canActivate: [ScreenGuard],
                        data: { title: 'Activity Tracker', titleI18n: 'Activity Tracker' },
                    },
                    {
                        path: 'reports',
                        loadChildren: () => import('../reports/reports.module').then(m => m.ReportsModule),
                        canActivate: [ScreenGuard],
                        data: { title: 'Reports', titleI18n: 'Reports' },
                    },
                ]
            },
];


@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class HomeRoutingModule { }
