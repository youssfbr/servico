import { Component, OnInit, AfterViewInit, ViewChildren, ElementRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControlName } from '@angular/forms';
import { fromEvent, merge, Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { MASKS, NgBrazilValidators } from 'ng-brazil';
import { GenericValidator, DisplayMessage, ValidationMessages } from '../../../shared/utils/generic-form-validation';

import { Client } from 'src/app/shared/types/client';
import { ClientsService } from 'src/app/shared/services/clients.service';


@Component({
  selector: 'app-clientes-form-reactive',
  templateUrl: './cliente-form.component.html'
})
export class ClienteFormComponent implements OnInit, AfterViewInit {

  @ViewChildren(FormControlName, { read: ElementRef }) formInputElements: ElementRef[];

  public MASKS = MASKS;

  cadastroForm: FormGroup;
  client: Client;
  success = false;
  errors: any[] = [];
  id!: number;
  errorMessage?: string;

  private genericValidator: GenericValidator;
  displayMessage: DisplayMessage = {};
  validationMessages: ValidationMessages =
  {
    name: {
      required: 'O nome é requerido.',
      minlength: 'O Nome precisa ter no mínimo 2 caracteres.',
      maxlength: 'O Nome precisa ter no máximo 60 caracteres.'
    },
    cpf: {
      required: 'Preencha o campo CPF.',
      cpf: 'CPF inválido.'
    }
  };

  constructor(
    private service: ClientsService,
    private fb: FormBuilder,
    private route: ActivatedRoute
    )
  {
    this.client = new Client();

    this.cadastroForm = this.fb.group({
      id: [{ value: '', disabled: true }],
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      cpf: ['', [Validators.required, NgBrazilValidators.cpf]],
      registerDate: [{ value: '', disabled: true }]
    });

    this.genericValidator = new GenericValidator(this.validationMessages);
  }

  ngOnInit(): void {

    this.id = this.route.snapshot.params.id;

    if (this.id) {
      this.service.getClientById(this.id).subscribe(
        response => { this.cadastroForm.setValue({
          id: response.id,
          registerDate: response.registerDate,
          name: response.name,
          cpf: response.cpf
        });

        this.client = Object.assign({}, this.client, response);

       }, () => this.client = new Client()
      );
    }

  }

  ngAfterViewInit(): void {
    let controlBlurs: Observable<any>[] = this.formInputElements
     .map((formControl: ElementRef) => fromEvent(formControl.nativeElement, 'blur'));

    merge(...controlBlurs).subscribe(() => {
      this.displayMessage = this.genericValidator.processMessages(this.cadastroForm);
    });
  }

  onSubmit(): void {
    if (this.cadastroForm.dirty && this.cadastroForm.valid) {
      this.cadastroForm.value.cpf = this.cadastroForm.value.cpf.replace(/\.|-/gm,'');
      this.client = Object.assign({}, this.client, this.cadastroForm.value);

      this.service.persist(this.client).subscribe(
        (response) => {
          this.success = true;
          this.errors = [];

          this.client = Object.assign({}, this.client, response);
          console.log(this.client);

          this.cadastroForm.get('id').setValue(response.id);
          this.cadastroForm.get('registerDate').setValue(response.registerDate);
        },
        (errorResponse) => {
          this.successFalse();
          this.errors = errorResponse.error.fields;
          this.errorMessage = 'Ocorreu um erro ao salvar/atualizar o cliente!'
        }
      );
    }
  }

  successFalse(): void {  }

}
