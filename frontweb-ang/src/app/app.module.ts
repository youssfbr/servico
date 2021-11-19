import { ClientsService } from './shared/services/clients.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HomeComponent } from './home/home.component';
import { TemplateModule } from './template/template.module';
import { ClientesModule } from './clientes/clientes.module';
import { ServicoPrestadoModule } from './servico-prestado/servico-prestado.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    TemplateModule,
    ClientesModule,
    ServicoPrestadoModule
  ],
  providers: [
    ClientsService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
