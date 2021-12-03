import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from 'src/app/shared/models/product';

@Component({
  selector: 'app-product-card-detail',
  templateUrl: './product-card-detail.component.html'
})
export class ProductCardDetailComponent {

   @Input()
   product: Product;

   @Output()
   status: EventEmitter<Product> = new EventEmitter();

   emitEvent(): void {
     this.status.emit(this.product);

   }


}
