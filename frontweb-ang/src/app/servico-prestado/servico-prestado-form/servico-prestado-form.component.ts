import { Component, OnInit } from '@angular/core';

import { Client } from './../../shared/types/client';
import { ServicoPrestado } from 'src/app/shared/types/servico-prestado';

import { ClientsService } from '../../shared/services/clients.service';

@Component({
  selector: 'app-servico-prestado-form',
  templateUrl: './servico-prestado-form.component.html',
  styleUrls: ['./servico-prestado-form.component.css'],
})
export class ServicoPrestadoFormComponent implements OnInit {

  clients: Client[] = [];
  servico: ServicoPrestado;

  constructor(private clienteService: ClientsService)
  {
    this.servico = new ServicoPrestado();
  }

  ngOnInit(): void {
    this.clienteService.getClients().subscribe(
      response => this.clients = response,
      error => console.error(error)
    );
  }

  onSubmit(): void {
    console.log(this.servico);
  }

}
