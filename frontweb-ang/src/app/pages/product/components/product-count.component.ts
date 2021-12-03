import { Product } from 'src/app/shared/models/product';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'product-count',
  template:
      `
      <div>
        Produtos ativos: <strong>{{ countActive() }}</strong> no total de <strong>{{ products.length }}</strong> produtos. <br /><br />
      </div>
  `
})
export class ProductCountComponent {

  @Input()
  products: Product[];

  countActive(): number {
    if (!this.products) return null;

    return this.products.filter((product: Product) => product.isActive).length;
  }

 }
