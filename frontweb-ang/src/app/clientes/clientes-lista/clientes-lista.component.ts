import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/shared/types/client';

import { ClientsService } from '../../shared/services/clients.service';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css'],
})
export class ClientesListaComponent implements OnInit {
  clients: Client[] = [];

  constructor(private service: ClientsService) {}

  ngOnInit(): void {
    this.getCli();
  }

  getCli() {
    this.service.getClients().subscribe(
      (response) => {
        this.clients = response;
      },
      (errorResponse) => {
        console.log(errorResponse);
      }
    );
  }
}
