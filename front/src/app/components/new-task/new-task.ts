import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Activity, ActivityNew, Category, Priority, Status } from '../../model/model';
import { ActivityService } from '../../service/services/activity.service';

@Component({
  selector: 'app-new-task',
  imports: [CommonModule, FormsModule],
  templateUrl: './new-task.html',
  styleUrl: './new-task.css',
})
export class NewTask {
  @Output() actividadCreada = new EventEmitter<Activity>();
  @Output() cerrar = new EventEmitter<void>();

  // Estado del componente
  guardando = false;
  error = '';
  cargandoDatos = true;

  // Datos del formulario
  actividad: Partial<ActivityNew> = {
    name: '',
    description: '',
    id_category: 1,
    id_priority: 1,
    id_status: 1,
  };

  // Datos para los selects (vienen del backend)
  categorias: Category[] = [];
  prioridades: Priority[] = [];
  estados: Status[] = [];

  constructor(private activityService: ActivityService) {
    this.inicializarFormulario();
  }








  ngOnInit() {
    this.cargarDatosSelects();
  }

  cargarDatosSelects() {
    this.cargandoDatos = true;

    // Cargar categorías, prioridades y estados en paralelo
    Promise.all([
      this.activityService.getCategories().toPromise(),
      this.activityService.getPriorities().toPromise(),
      this.activityService.getStatus().toPromise()
    ]).then(([categorias, prioridades, estados]) => {
      this.categorias = categorias || [];
      this.prioridades = prioridades || [];
      this.estados = estados || [];

      // Establecer valores por defecto después de cargar los datos
      if (this.categorias.length > 0) {
        this.actividad.id_category = this.categorias[0].id;
      }
      if (this.prioridades.length > 0) {
        this.actividad.id_priority = this.prioridades[0].id;
      }
      if (this.estados.length > 0) {
        this.actividad.id_status = this.estados[0].id;
      }

      this.cargandoDatos = false;
    }).catch(error => {
      this.error = 'Error al cargar los datos del formulario';
      this.cargandoDatos = false;
      console.error('Error cargando datos:', error);
    });
  }





  private inicializarFormulario() {
    this.actividad = {
      name: '',
      description: '',
      id_category: this.categorias.length > 0 ? this.categorias[0].id : 0,
      id_status: this.estados.length > 0 ? this.estados[0].id : 0,
      id_priority: this.prioridades.length > 0 ? this.prioridades[0].id : 0
    };
    this.error = '';
  }


  onCerrar() {
    this.cerrar.emit();
    this.inicializarFormulario();
  }

  onGuardar() {
    if (!this.validarFormulario()) {
      return;
    }

    this.guardando = true;
    this.error = '';


    // Crear el objeto con solo los IDs (sin objetos completos)
    const actividadParaEnviar: ActivityNew = {
      name: this.actividad.name?.trim() || '',
      description: this.actividad.description?.trim() || '',
      id_category: Number(this.actividad.id_category),
      id_status: Number(this.actividad.id_status),
      id_priority: Number(this.actividad.id_priority)
    };

    this.activityService.createTask(actividadParaEnviar).subscribe({
      next: () => {
        this.guardando = false;
        this.inicializarFormulario();
        this.actividadCreada.emit();
        this.cerrar.emit();
      },
      error: (err) => {
        this.error = err.message || 'Error al crear la actividad';
        this.guardando = false;
      }
    });
  }

  private validarFormulario(): boolean {
    if (!this.actividad.name?.trim()) {
      this.error = 'El nombre es obligatorio';
      return false;
    }
    if (!this.actividad.description?.trim()) {
      this.error = 'La descripción es obligatoria';
      return false;
    }
    return true;
  }
}
