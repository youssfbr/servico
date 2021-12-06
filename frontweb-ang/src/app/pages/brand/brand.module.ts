import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { BrandRoutingModule } from './brand-routing.module';

import { BrandFormComponent } from './brand-form/brand-form.component';
import { SharedComponentsModule } from 'src/app/shared/components/shared-components.module';
import { BrandListComponent } from './brand-list/brand-list.component';


@NgModule({
  declarations: [
    BrandListComponent,
    BrandFormComponent
  ],
  imports: [
    CommonModule,
    BrandRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    SharedComponentsModule
  ],
  exports: [
    BrandListComponent,
    BrandFormComponent
  ]
})
export class BrandModule { }


