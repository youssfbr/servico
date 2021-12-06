import { Brand } from './../../../shared/models/Brand';
import { BrandService } from './../../../shared/services/brand.service';
import { Component, OnInit } from '@angular/core';
//import { Brand } from 'src/app/shared/models/Brand';

@Component({
  selector: 'app-brand-list',
  templateUrl: './brand-list.component.html'
})
export class BrandListComponent implements OnInit {

  brands: Brand[] = [];
  brandSelect: Brand;
  successMessage?: string;
  errorMessage?: string;

  constructor(private service: BrandService) {
    this.brandSelect = new Brand();
  }

  ngOnInit(): void {
    this.getBrands();
  }

  getBrands() {
    this.service.getBrands().subscribe(
      response => this.brands = response,
      errorResponse => console.log(errorResponse)
    );
  }

  BeforeDelete(brand: Brand): void {
    this.brandSelect = brand;
  }

  DeleteBrand(): void {
    this.service
        .delete(this.brandSelect)
        .subscribe(
          response => {
            this.successMessage = 'Cliente deletado com sucesso.';
            this.getBrands();
        },
          error => this.errorMessage = 'Ocorreu um erro ao deletar o cliente!'
        );
        this.successMessage = '';
        this.errorMessage = '';

  }

}
