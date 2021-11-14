import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Client } from '../types/client';

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  constructor(private http: HttpClient) { }

  persist(client: Client): Observable<Client> {

    return this.http.post<Client>('http://localhost:8080/api/clients', client);
  }

  getClient(): Client {
    let client : Client = new Client();
    client.name = "Alisson";
    client.cpf = '4525245245';
    return client;
  }
}

