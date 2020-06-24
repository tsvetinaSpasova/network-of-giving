import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { CreateCharityComponent } from './components/create-charity/create-charity.component';
import { WhyToGiveCharityComponent } from './components/why-to-give-charity/why-to-give-charity.component';
import { CharityInformationComponent } from './components/charity-information/charity-information.component';
import { EditCharityComponent } from './components/edit-charity/edit-charity.component'
const routes: Routes = [{ path: 'home', component: HomeComponent }, 
    { path: 'login', component: LoginComponent },
    { path: 'sign-up', component: RegisterComponent },
    { path: 'create', component: CreateCharityComponent},
    { path: 'reasons-to-give-to-charity', component: WhyToGiveCharityComponent},
    { path: 'charity/:id', component: CharityInformationComponent},
    { path: 'edit/:id', component: EditCharityComponent}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
