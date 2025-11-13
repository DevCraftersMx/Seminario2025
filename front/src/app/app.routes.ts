import { Routes } from '@angular/router';
import { App } from './app';
import { Dashboard } from './pages/dashboard/dashboard';
import { Home } from './pages/home/home';

export const routes: Routes = [
    { path: '', component: Home}, 
    { path: 'dashboard', component: Dashboard },
];
