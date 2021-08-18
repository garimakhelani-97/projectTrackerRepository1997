import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './sessions/login/login.component';
import { RegisterComponent } from './sessions/register/register.component';
import { AdminLayoutComponent } from './theme/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from './theme/auth-layout/auth-layout.component';
import { SharedModule } from '@shared';
import { AuthGuard } from '@core/security/guards/auth.guard';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  //{ path: '', redirectTo: 'auth/login', pathMatch: 'full' },
  { path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(mod => mod.AdminModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'auth',
    component: AuthLayoutComponent,
    children: [
      { path: 'login', component: LoginComponent, data: { title: 'Login', titleI18n: 'login' } },
      {
        path: 'register',
        component: RegisterComponent,
        data: { title: 'Register', titleI18n: 'register' },
      },
    ],
  },
 { path: '**', redirectTo: 'auth/login' },
];


const COMPONENTS = [LoginComponent, RegisterComponent];
const COMPONENTS_DYNAMIC = [];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      useHash: false,
      enableTracing: false
    }),
    SharedModule
  ],
  declarations: [...COMPONENTS, ...COMPONENTS_DYNAMIC],
  entryComponents: COMPONENTS_DYNAMIC,
  exports: [RouterModule]
})
export class AppRoutingModule {}
