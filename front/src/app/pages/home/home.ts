import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  name = 'Josué';

  constructor(private router: Router) {}

  ingresar() {
    console.log('Botón Ingresar clickeado');
    this.router.navigate(['/dashboard']);
  }
}
