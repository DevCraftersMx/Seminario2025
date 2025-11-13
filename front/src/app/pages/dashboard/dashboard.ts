import { Component } from '@angular/core';
import { Activity, ActivityNew } from '../../model/model';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ActivityService } from '../../service/services/activity.service';
import { NewTask } from '../../components/new-task/new-task';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, NewTask],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {

  activities: Activity[] = [];
  loading = true;
  error = '';
  mostrarModalCrear = false;
  eliminando = false;

  constructor(
    private router: Router,
    private activityService: ActivityService 
  ) {}

  ngOnInit() {
    this.loadActivities();
  }

  loadActivities() {
    this.loading = true;
    this.error = '';

    this.activityService.getAllTasks().subscribe({
      next: (data) => {
        this.activities = data.data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Error al cargar las actividades';
        this.loading = false;
        console.error('Error:', err);
      }
    });
  }

  volverAlLogin() {
    this.router.navigate(['/']);
  }

  // Métodos adicionales
  getPriorityColor(priority: string): string {
    switch (priority) {
      case 'Crítica':
        return 'bg-red-100 text-red-800'; // rojo fuerte
      case 'Urgente':
        return 'bg-orange-100 text-orange-800'; // naranja
      case 'Moderado':
        return 'bg-yellow-100 text-yellow-800'; // amarillo
      case 'Normal':
        return 'bg-blue-100 text-blue-800'; // azul
      case 'Programada':
        return 'bg-green-100 text-green-800'; // verde
      case 'Opcional':
        return 'bg-gray-100 text-gray-800'; // gris
      default:
        return 'bg-slate-100 text-slate-800'; // por si llega algo no reconocido
    }
  }


  getStatusColor(status: string): string {
    switch (status) {
      case 'Nuevo':
        return 'bg-gray-100 text-gray-800'; // estado recién creado
      case 'En progreso':
        return 'bg-blue-100 text-blue-800'; // actividad en curso
      case 'Terminado':
        return 'bg-green-100 text-green-800'; // finalizado con éxito
      case 'Pendiente':
        return 'bg-yellow-100 text-yellow-800'; // a la espera de acción
      default:
        return 'bg-slate-100 text-slate-800'; // por si llega un valor desconocido
    }
  }


  getactividadesEnProgreso() {
    return this.activities.filter(a => a.id_status.id === 2).length;
  }

  getactividadesCompletas() {
    return this.activities.filter(a => a.id_status.id === 3).length;
  }


  // Métodos simples para el modal
  crearNuevaActividad() {
    this.mostrarModalCrear = true;
  }

  cerrarModal() {
    this.mostrarModalCrear = false;
  }

  onActividadCreada() {
    //this.activities.unshift(nuevaActividad);
    alert('Actividad creada exitosamente');
    this.cerrarModal();
    this.loadActivities();
  }

  editarActividad(activity: Activity) {
    console.log('Editar actividad:', activity);
    // Aquí va tu lógica para abrir modal/formulario de edición
  }

  eliminarActividad(activityId: number) {
    console.log('Eliminar actividad ID:', activityId);
    if (confirm('¿Estás seguro de que quieres eliminar esta actividad?')) {
      this.eliminando = true;
      
      this.activityService.deleteTask(activityId).subscribe({
        next: (response) => {
          alert('Actividad eliminada exitosamente');
          this.loadActivities();
          this.eliminando = false;
        },
        error: (err) => {
          alert('Error al eliminar la actividad');
          this.eliminando = false;
        }
      });
    }
  }

}
