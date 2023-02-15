import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReviewHomeComponent } from './review-home/review-home.component';
import { LoginComponent } from './login/login.component';
import { ReviewFormComponent } from './review-form/review-form.component';
import { AuthGuard } from './services/guards/auth.guard';

const routes: Routes = [
  {
    path : '',
    component : ReviewHomeComponent
  },
  {
    path : 'login',
    component : LoginComponent
  },
  {
    path : 'addReview',
    canActivate :[AuthGuard],
    component : ReviewFormComponent
  },
  {
    path : '**',
    redirectTo : ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],

exports: [RouterModule]
})
export class AppRoutingModule { }
