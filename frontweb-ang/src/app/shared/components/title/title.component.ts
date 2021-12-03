import { Component } from '@angular/core';

@Component({
  selector: 'app-title',
  template:
  `
  <h1 class="mt-4"><ng-content select=".title"></ng-content></h1>
  <ol class="breadcrumb mb-4">
    <li class="breadcrumb-item active"> <ng-content select=".subtitle"></ng-content> </li>
  </ol>
  `
})
export class TitleComponent  { }
