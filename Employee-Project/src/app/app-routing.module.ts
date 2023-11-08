import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SideNavComponent } from './side-nav/side-nav.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';

const routes: Routes = [
  { path:'', component: SideNavComponent,
    children: [
      { path: 'newEmployee', component: HomeComponent },
      { path: 'employeeList', component: EmployeeListComponent },
    ]
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
