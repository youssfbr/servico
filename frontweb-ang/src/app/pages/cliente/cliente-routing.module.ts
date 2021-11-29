import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClienteListaComponent } from './cliente-lista/cliente-lista.component';
import { ClienteFormComponent } from './cliente-form/cliente-form.component';

const routes: Routes = [
  { path: 'cliente-form', component: ClienteFormComponent },
  { path: 'cliente-form/:id', component: ClienteFormComponent },
  { path: 'cliente-list', component: ClienteListaComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
