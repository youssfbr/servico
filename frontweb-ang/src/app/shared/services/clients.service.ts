import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Client } from '../types/client';

@Injectable({
  providedIn: 'root',
})
export class ClientsService {

  url: string = 'http://localhost:8080/api/clients';

  constructor(private http: HttpClient) {}

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.url);
  }

  getClientById(id: number): Observable<Client> {
    return this.http.get<Client>(`http://localhost:8080/api/clients/${id}`);
  }

  persist(client: Client): Observable<Client> {
    return client.id
      ? this.http.put<Client>(`${this.url}/${client.id}`, client)
      : this.http.post<Client>(this.url, client);
  }

  delete(client: Client): Observable<any> {
    return this.http.delete<any>(`${this.url}/${client.id}`);
  }
  
}
