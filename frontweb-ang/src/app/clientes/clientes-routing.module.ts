import { ClientesFormReactiveComponent } from './clientes-form-reactive/clientes-form-reactive.component';
import { ClientesListaComponent } from './clientes-lista/clientes-lista.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClienteFormComponent } from './cliente-form/cliente-form.component';

const routes: Routes = [
  { path: 'cliente-form', component: ClienteFormComponent },
  { path: 'cliente-form/:id', component: ClienteFormComponent },
  { path: 'cliente-list', component: ClientesListaComponent },
  { path: 'cliente-form-reactive', component: ClientesFormReactiveComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
