import { Product } from '../../../shared/models/product';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-produto-dashboard',
  templateUrl: './product-dashboard.component.html'
})
export class ProductDashboardComponent implements OnInit {

  products : Product[] = [];

  constructor() { }

  ngOnInit(): void {
    this.products = [
      {
        id: 1,
        name: 'Celular',
        price: 100,
        image: 'mobile.jpg',
        isActive: true
      },
      {
        id: 2,
        name: 'Go Pro',
        price: 200,
        image: 'gopro.jpg',
        isActive: true
      },
      {
        id: 3,
        name: 'Notebook',
        price: 3000,
        image: 'laptop.jpg',
        isActive: true
      },
      {
        id: 4,
        name: 'Mouse',
        price: 150,
        image: 'mouse.jpg',
        isActive: true
      },
      {
        id: 5,
        name: 'Teclado',
        price: 134,
        image: 'keyboard.jpg',
        isActive: true
      },
      {
        id: 6,
        name: 'Fone de ouvido',
        price: 130,
        image: 'headset.jpg',
        isActive: false
      }
    ]
  }

  ChangeStatus(event: Product): void {
    event.isActive = !event.isActive;
  }

}
