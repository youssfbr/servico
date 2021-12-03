import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';

import { ProductDashboardComponent } from './product-dashboard/product-dashboard.component';
import { ProductCardDetailComponent } from './components/product-card-detail/product-card-detail.component';
import { ProductCountComponent } from './components/product-count.component';

import { registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';
registerLocaleData(localePt);

import { SharedComponentsModule } from 'src/app/shared/components/shared-components.module';

@NgModule({
  declarations: [
    ProductDashboardComponent,
    ProductCardDetailComponent,
    ProductCountComponent
  ],
  imports: [
    CommonModule,
    ProductRoutingModule,
    SharedComponentsModule
  ],
  exports: [ ]
})
export class ProductModule { }
