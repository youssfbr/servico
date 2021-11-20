import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ServicoPrestado } from 'src/app/shared/types/servico-prestado';

import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ServicoPrestadoService {

  apiUrl: string = environment.apiURLBase + '/api/servicos';

  constructor(private http: HttpClient) {}

  persist(servicoPrestado: ServicoPrestado): Observable<ServicoPrestado> {
    return this.http.post<ServicoPrestado>(this.apiUrl, servicoPrestado);
  }

}
