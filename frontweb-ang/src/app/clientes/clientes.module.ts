import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClientesRoutingModule } from './clientes-routing.module';
import { ClienteFormComponent } from './cliente-form/cliente-form.component';
import { ClientesListaComponent } from './clientes-lista/clientes-lista.component';
import { ClientesFormReactiveComponent } from './clientes-form-reactive/clientes-form-reactive.component';

//import { NgBrazil } from 'ng-brazil';
import { TextMaskModule } from '@myndmanagement/text-mask';
//import { TextMaskModule } from 'angular2-text-mask';
//import { CustomFormsModule } from 'ng2-validation';

@NgModule({
  declarations: [
    ClienteFormComponent,
    ClientesListaComponent,
    ClientesFormReactiveComponent
  ],
  imports: [
    CommonModule,
    ClientesRoutingModule,
    FormsModule,
    ReactiveFormsModule,
   // NgBrazil,
    TextMaskModule,
  ],
  exports: [
    ClienteFormComponent,
    ClientesListaComponent,
    ClientesFormReactiveComponent
  ]
})
export class ClientesModule { }
