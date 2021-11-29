import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { ServicoPrestadoRoutingModule } from './servico-prestado-routing.module';
import { ServicoPrestadoFormComponent } from './servico-prestado-form/servico-prestado-form.component';
import { ServicoPrestadoListaComponent } from './servico-prestado-lista/servico-prestado-lista.component';

import { TextMaskModule } from '@myndmanagement/text-mask';
import { CustomFormsModule } from 'ng2-validation';


@NgModule({
  declarations: [
    ServicoPrestadoFormComponent,
    ServicoPrestadoListaComponent
  ],
  imports: [
    CommonModule,
    ServicoPrestadoRoutingModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    TextMaskModule,
    CustomFormsModule
  ],
  exports: [
    ServicoPrestadoFormComponent,
    ServicoPrestadoListaComponent
  ]
})
export class ServicoPrestadoModule { }

