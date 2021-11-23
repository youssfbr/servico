import { Component, OnInit, AfterViewInit, ViewChildren, ElementRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControlName } from '@angular/forms';
import { fromEvent, merge, Observable } from 'rxjs';

import { MASKS, NgBrazilValidators } from 'ng-brazil';
import { GenericValidator } from '../../shared/utils/generic-form-validation';

import { Client } from 'src/app/shared/types/client';
import { ClientsService } from 'src/app/shared/services/clients.service';

@Component({
  selector: 'app-clientes-form-reactive',
  templateUrl: './clientes-form-reactive.component.html',
  styleUrls: ['./clientes-form-reactive.component.css']
})
export class ClientesFormReactiveComponent implements OnInit, AfterViewInit {

  @ViewChildren(FormControlName, { read: ElementRef }) formInputElements: ElementRef[];

  public MASKS = MASKS;

  displayMessage: { [key: string]: string } = {};
  private genericValidator: GenericValidator;

  cadastroForm: FormGroup;
  client: Client;
  formResult: string = '';

  success = false;
  errors: any[] = [];
  id!: number;
  errorMessage?: string;

  constructor(private service: ClientsService, private fb: FormBuilder)
  {
    this.client = new Client();

    this.cadastroForm = this.fb.group({
      id: [{ value: '', disabled: true }],
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      cpf: ['', [Validators.required, NgBrazilValidators.cpf]],
      registerDate: [{ value: '', disabled: true }]
    });

    this.genericValidator = new GenericValidator();
  }

  ngOnInit(): void { }

  ngAfterViewInit(): void {
    let controlBlurs: Observable<any>[] = this.formInputElements
     .map((formControl: ElementRef) => fromEvent(formControl.nativeElement, 'blur'));

    merge(...controlBlurs).subscribe(() => {
      this.displayMessage = this.genericValidator.processMessages(this.cadastroForm);
    });
  }

  adicionarUsuario(): void {
    if (this.cadastroForm.dirty && this.cadastroForm.valid) {
      this.client = Object.assign({}, this.client, this.cadastroForm.value);
      this.formResult = JSON.stringify(this.cadastroForm.value);
      console.log('---form', this.cadastroForm.value);
    }
    else {
      this.formResult = "Não submeteu!"
    }
  }

  onSubmit(): void {
    if (this.cadastroForm.dirty && this.cadastroForm.valid) {
      this.cadastroForm.value.cpf = this.cadastroForm.value.cpf.replace(/\.|-/gm,'');
      this.client = Object.assign({}, this.client, this.cadastroForm.value);
      console.log(this.client);

      this.service.persist(this.client).subscribe(
        (response) => {
          this.success = true;
          this.errors = [];

          this.client = Object.assign({}, this.client, response);
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
