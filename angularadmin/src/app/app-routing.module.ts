import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderListComponent } from './order-list/order-list.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';



const routes: Routes = [
  { 
    path: '', 
    children: [
      { path: 'orders', component: OrderListComponent},
      { path: '', component: HomeComponent},
      { path: 'home', component: HomeComponent}
      // add other routes that should include the menu bar
    ]
  },
  { path: 'login', component: LoginComponent},
 // { path: '**', component: NotFoundComponent } // default component for undefined routes
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
