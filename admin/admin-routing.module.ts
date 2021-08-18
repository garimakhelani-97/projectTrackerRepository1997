import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MetaSectionComponent } from './components/meta-section/meta-section.component';
import { AdminComponent } from './admin.component';
import { MetaSectionResolverService } from './resolvers/meta-section-resolver.service';
import { MetaConfigComponent } from './components/meta-config/meta-config.component';

const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    children: [
      {
        path: '',
        component: MetaConfigComponent,
        resolve: {dynamicFormsConfig: MetaSectionResolverService}

      },
      {
        path: 'dynamic-form',
        component: MetaSectionComponent,
        resolve: { dynamicFormsConfig: MetaSectionResolverService }

      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [MetaSectionResolverService]
})
export class AdminRoutingModule { }
