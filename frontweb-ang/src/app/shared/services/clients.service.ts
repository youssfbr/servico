import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Client } from '../types/client';

import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ClientsService {

  apiUrl: string = environment.apiURLBase + '/api/clients';

  constructor(private http: HttpClient) {}

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.apiUrl);
  }

  getClientById(id: number): Observable<Client> {
    return this.http.get<Client>(`${this.apiUrl}/${id}`);
  }

  persist(client: Client): Observable<Client> {
    return client.id
      ? this.http.put<Client>(`${this.apiUrl}/${client.id}`, client)
      : this.http.post<Client>(this.apiUrl, client);
  }

  delete(client: Client): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${client.id}`);
  }

}
