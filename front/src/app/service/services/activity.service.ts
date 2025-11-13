import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Activity, ActivityNew, Category, Priority, Status } from '../../model/model';
import { map, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ApiResponse } from '../../model/apiResponseModel';

@Injectable({
  providedIn: 'root',
})
export class ActivityService {
  private findAllCategories = `${environment.apiUrl}/task/findAllCategories`;
  private findAllStatus = `${environment.apiUrl}/task/findAllStatus`;
  private findAllPriorities = `${environment.apiUrl}/task/findAllPriorities`;

  private apiGetAllTasks = `${environment.apiUrl}/task/findAllTasks`;
  private apiCreateTask = `${environment.apiUrl}/task/createTask`;
  private apiUpdateTask = `${environment.apiUrl}/task/updateTask`;
  private apiDeleteTask = `${environment.apiUrl}/task/deleteTask`;

  constructor(private http: HttpClient) { }

  getAllTasks(): Observable<ApiResponse<Activity[]>> {
    return this.http.get<ApiResponse<Activity[]>>(this.apiGetAllTasks);
  }

  createTask(activity: Partial<ActivityNew>): Observable<void> {
    return this.http.post<ApiResponse<void>>(this.apiCreateTask, activity).pipe(
      map(response => {
        if (response.success) {
          return response.data;
        } else {
          throw new Error(response.message || 'Error al crear la actividad');
        }
      })
    );
  }

  updateTask(id: number, activity: Partial<Activity>): Observable<Activity> {
    return this.http.post<ApiResponse<Activity>>(`${this.apiUpdateTask}/${id}`, activity).pipe(
      map(response => {
        if (response.success) {
          return response.data;
        } else {
          throw new Error(response.message || 'Error al actualizar la actividad');
        }
      })
    );
  }

  deleteTask(id: number): Observable<void> {
    return this.http.get<ApiResponse<void>>(`${this.apiDeleteTask}?id=${id}`).pipe(
      map(response => {
        if (!response.success) {
          throw new Error(response.message || 'Error al eliminar la actividad');
        }
      })
    );
  }



  getCategories(): Observable<Category[]> {
    return this.http.get<ApiResponse<Category[]>>(this.findAllCategories).pipe(
      map(response => {
        if (response.success) {
          return response.data;
        } else {
          throw new Error(response.message || 'Error');
        }
      })
    );
  }
  getStatus(): Observable<Status[]> {
    return this.http.get<ApiResponse<Status[]>>(this.findAllStatus).pipe(
      map(response => {
        if (response.success) {
          return response.data;
        } else {
          throw new Error(response.message || 'Error');
        }
      })
    );
  }
  getPriorities(): Observable<Priority[]> {
    return this.http.get<ApiResponse<Priority[]>>(this.findAllPriorities).pipe(
      map(response => {
        if (response.success) {
          return response.data;
        } else {
          throw new Error(response.message || 'Error');
        }
      })
    );
  }










}
