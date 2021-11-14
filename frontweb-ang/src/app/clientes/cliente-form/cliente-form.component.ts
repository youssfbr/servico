import { Client } from '../../shared/types/client';
import { Component, OnInit } from '@angular/core';

import { ClientsService } from '../../shared/services/clients.service';

@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
  styleUrls: ['./cliente-form.component.css'],
})
export class ClienteFormComponent implements OnInit {

  client: Client;

  constructor( private service: ClientsService ) {
    this.client = new Client();
  }

  ngOnInit(): void {}

  onSubmit(): void {
    this.service
      .persist(this.client)
      .subscribe( response => {
        console.log(response);
      })
  }
}
