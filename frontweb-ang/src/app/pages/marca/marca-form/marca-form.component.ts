import { Component, OnInit, AfterViewInit, ViewChildren, ElementRef } from '@angular/core';
import { FormBuilder, FormControlName, FormGroup, Validators } from '@angular/forms';
import { fromEvent, merge, Observable } from 'rxjs';

import { GenericValidator, DisplayMessage, ValidationMessages } from 'src/app/shared/utils/generic-form-validation';

import { Marca } from 'src/app/shared/types/marca';


@Component({
  selector: 'app-marca-form',
  templateUrl: './marca-form.component.html'
})
export class MarcaFormComponent implements OnInit, AfterViewInit {

  @ViewChildren(FormControlName, { read: ElementRef }) formInputElements: ElementRef[];

  marca: Marca;
  success = false;
  errors: any[] = [];
  errorMessage?: string;

  private genericValidator: GenericValidator;
  displayMessage: DisplayMessage = {};
  validationMessages: ValidationMessages =
  {
    marca: {
      required: 'A marca é requerida.',
    }
  };

  form: FormGroup = this.fb.group({
    id: [''],
    marca: ['', [Validators.required]]
  });

  constructor(
    private fb: FormBuilder
    ) {
    this.marca = new Marca();
    this.genericValidator = new GenericValidator(this.validationMessages);
  }

  ngOnInit(): void {  }

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
      console.log(this.form.value);
      this.marca = Object.assign({}, this.marca, this.form.value);

     console.log(this.marca);
    }

  }

  successFalse(): void {
    this.success = false;
  }

}
