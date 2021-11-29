import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MarcaRoutingModule } from './marca-routing.module';

import { MarcaFormComponent } from './marca-form/marca-form.component';


@NgModule({
  declarations: [
    MarcaFormComponent
  ],
  imports: [
    CommonModule,
    MarcaRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [
    MarcaFormComponent
  ]
})
export class MarcaModule { }


