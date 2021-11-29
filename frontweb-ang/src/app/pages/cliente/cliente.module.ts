import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClientesRoutingModule } from './cliente-routing.module';
import { ClienteFormComponent } from './cliente-form/cliente-form.component';
import { ClienteListaComponent } from './cliente-lista/cliente-lista.component';

import { TextMaskModule } from '@myndmanagement/text-mask';

@NgModule({
  declarations: [
    ClienteFormComponent,
    ClienteListaComponent,
  ],
  imports: [
    CommonModule,
    ClientesRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    TextMaskModule,
  ],
  exports: [
    ClienteFormComponent,
    ClienteListaComponent,
  ]
})
export class ClienteModule { }
