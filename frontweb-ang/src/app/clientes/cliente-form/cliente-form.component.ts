import { Client } from '../../shared/types/client';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';

import { ClientsService } from '../../shared/services/clients.service';

@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
  styleUrls: ['./cliente-form.component.css'],
})
export class ClienteFormComponent implements OnInit {
  client: Client;
  success = false;
  errors: any[] = [];
  id!: number;

  constructor(private service: ClientsService, private route: ActivatedRoute) {
    this.client = new Client();
  }

  ngOnInit(): void {
    if (
      this.route.snapshot &&
      this.route.snapshot.params &&
      this.route.snapshot.params.id
    ) {
      this.id = this.route.snapshot.params.id;

      this.service.getClientById(this.id).subscribe(
        (response) => (this.client = response),
        () => (this.client = new Client())
      );
    }
  }

  onSubmit(): void {
    this.service.persist(this.client).subscribe(
      (response) => {
        this.success = true;
        this.errors = [];
        this.client = response;
      },
      (errorResponse) => {
        this.successFalse();
        this.errors = errorResponse.error.fields;
      }
    );
  }

  successFalse(): void {
    this.success = false;
  }
}
