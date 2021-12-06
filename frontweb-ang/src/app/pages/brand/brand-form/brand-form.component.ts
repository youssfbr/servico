import { Brand } from 'src/app/shared/models/Brand';
import { BrandService } from './../../../shared/services/brand.service';
import { Component, OnInit, AfterViewInit, ViewChildren, ElementRef } from '@angular/core';
import { FormBuilder, FormControlName, FormGroup, Validators } from '@angular/forms';
import { fromEvent, merge, Observable } from 'rxjs';

import { GenericValidator, DisplayMessage, ValidationMessages } from 'src/app/shared/utils/generic-form-validation';

import { Marca } from 'src/app/shared/types/marca';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-marca-form',
  templateUrl: './brand-form.component.html'
})
export class BrandFormComponent implements OnInit, AfterViewInit {

  @ViewChildren(FormControlName, { read: ElementRef }) formInputElements: ElementRef[];

  brand: Brand;
  success = false;
  errors: any[] = [];
  id!: number;
  errorMessage?: string;

  private genericValidator: GenericValidator;
  displayMessage: DisplayMessage = {};
  validationMessages: ValidationMessages =
  {
    name: {
      required: 'A marca é requerida.',
    }
  };

  form: FormGroup = this.fb.group(
  {
    id: [{ value: '', disabled: true }],
    name: ['', [Validators.required]]
  });

  constructor(
    private service: BrandService,
    private route: ActivatedRoute,
    private fb: FormBuilder
    ) {
    this.brand = new Brand();
    this.genericValidator = new GenericValidator(this.validationMessages);
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params.id;

    if (this.id) {
      this.service.getClientById(this.id).subscribe(
        response => { this.form.setValue({
          id: response.id,
          name: response.name
        });

        this.brand = Object.assign({}, this.brand, response);

       }, () => this.brand = new Brand()
      );
    }
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
      this.brand = Object.assign({}, this.brand, this.form.value);

      this.service.persist(this.brand).subscribe(
        (response) => {
          this.success = true;
          this.errors = [];

          this.brand = Object.assign({}, this.brand, response);
          this.form.get('id').setValue(response.id);
        },
        (errorResponse) => {
          this.successFalse();
          this.errors = errorResponse.error.fields;
          this.errorMessage = 'Ocorreu um erro ao salvar/atualizar o cliente!'
        }
      )
    }
  }



  successFalse(): void {
    this.success = false;
  }

}
