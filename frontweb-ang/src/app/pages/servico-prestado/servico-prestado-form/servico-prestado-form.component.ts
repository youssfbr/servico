import { Component, OnInit, AfterViewInit, ViewChildren, ElementRef } from '@angular/core';
import { FormBuilder, FormControlName, FormGroup, Validators } from '@angular/forms';
import { fromEvent, merge, Observable } from 'rxjs';

import { Client } from './../../../shared/types/client';
import { ServicoPrestado } from 'src/app/shared/types/servico-prestado';

import { MASKS } from 'ng-brazil';
import { GenericValidator, DisplayMessage, ValidationMessages } from 'src/app/shared/utils/generic-form-validation';
import { CustomValidators } from 'ng2-validation';

import { ClientsService } from '../../../shared/services/clients.service';
import { ServicoPrestadoService } from '../../../shared/services/servico-prestado.service';

@Component({
  selector: 'app-servico-prestado-form',
  templateUrl: './servico-prestado-form.component.html'
})
export class ServicoPrestadoFormComponent implements OnInit, AfterViewInit {

  @ViewChildren(FormControlName, { read: ElementRef }) formInputElements: ElementRef[];

  public MASKS = MASKS;
  public maskDate = [/[0-3]/, /\d/, '/', /[0-1]/,  /[0-2]/, '/', /\d/, /\d/, /\d/, /\d/];

  clients: Client[] = [];
  servico: ServicoPrestado;

  success = false;
  errors: any[] = [];
  errorMessage?: string;

  private genericValidator: GenericValidator;
  displayMessage: DisplayMessage = {};
  validationMessages: ValidationMessages =
  {
    clientId: {
      required: 'O nome do cliente é requerido.',
    },
    description: {
      required: 'A descrição é obrigatória.'
    },
    date: {
      required: 'A data é obrigatória.',
      date: 'Data inválida.'
    },
    price: {
      required: 'O valor do serviço é obrigatório.'
    }
  };

  form: FormGroup = this.fb.group({
    id: [''],
    description: ['', [Validators.required]],
    date: ['', [Validators.required, CustomValidators.date]],
    price: ['', [Validators.required]],
    clientId: ['', [Validators.required]],
  });

  constructor(
    private clienteService: ClientsService,
    private servicoPrestadoService: ServicoPrestadoService,
    private fb: FormBuilder
  ) {
    this.servico = new ServicoPrestado();
    this.genericValidator = new GenericValidator(this.validationMessages);
  }

  ngOnInit(): void {
    this.clienteService.getClients().subscribe(
      (response) => (this.clients = response),
      (error) => console.log(error)
    );
  }

  ngAfterViewInit(): void {
      let controlBlurs: Observable<any>[] = this.formInputElements
      .map((formControl: ElementRef) => fromEvent(formControl.nativeElement, 'blur'));

      merge(...controlBlurs).subscribe(() => {
        this.displayMessage = this.genericValidator.processMessages(this.form);
      });
  }

  onSubmit(form): void {
    if (form.dirty && form.valid)
    {
      this.servico = Object.assign({}, this.servico, form.value)

      this.servicoPrestadoService.persist(this.servico).subscribe(
        (response) => {
          this.success = true;
          this.errors = [];
          this.servico = new ServicoPrestado();
          this.servico = response;
        }
        ,
        (errorResponse) => {
          this.successFalse();
          this.errors = errorResponse.error.fields;
          this.errorMessage = 'Ocorreu um erro ao salvar/atualizar o serviço prestado!'
        }
      );
    }
  }

  successFalse(): void {
    this.success = false;
  }
  
}
