import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';

import { TemplateModule } from './template/template.module';
import { ClienteModule } from './pages/cliente/cliente.module';
import { ServicoPrestadoModule } from './pages/servico-prestado/servico-prestado.module';
import { MarcaModule } from './pages/marca/marca.module';

import { ClientsService } from './shared/services/clients.service';
import { ServicoPrestadoService } from './shared/services/servico-prestado.service';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    TemplateModule,
    ClienteModule,
    MarcaModule,
    ServicoPrestadoModule,
  ],
  providers: [
    ClientsService,
    ServicoPrestadoService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
