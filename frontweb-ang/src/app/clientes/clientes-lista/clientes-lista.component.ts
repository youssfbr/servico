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
  clientSelect: Client;
  successMessage?: string;
  errorMessage?: string;

  constructor(private service: ClientsService) {
    this.clientSelect = new Client();
  }

  ngOnInit(): void {
    this.getCli();
  }

  getCli() {
    this.service.getClients().subscribe(
      response => this.clients = response ,
      errorResponse => console.log(errorResponse)
    );
  }

  BeforeDelete(client: Client): void {
    this.clientSelect = client;
  }

  DeleteClient(): void {
    this.service
        .delete(this.clientSelect)
        .subscribe(
          response => {
            this.successMessage = 'Cliente deletado com sucesso.';
            this.getCli();
        },
          error => this.errorMessage = 'Ocorreu um erro ao deletar o cliente!'
        );
        this.successMessage = '';
        this.errorMessage = '';
  }

}
