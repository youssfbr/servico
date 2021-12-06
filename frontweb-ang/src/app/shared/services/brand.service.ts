import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Brand } from '../models/Brand';

import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class BrandService {

  apiUrl: string = environment.apiURLBase + '/api/brands';

  constructor(private http: HttpClient) {}

  getBrands(): Observable<Brand[]> {
    return this.http.get<Brand[]>(this.apiUrl);
  }

  getClientById(id: number): Observable<Brand> {
    return this.http.get<Brand>(`${this.apiUrl}/${id}`);
  }

  persist(brand: Brand): Observable<Brand> {
    return brand.id
      ? this.http.put<Brand>(`${this.apiUrl}/${brand.id}`, brand)
      : this.http.post<Brand>(this.apiUrl, brand);
  }

  delete(brand: Brand): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${brand.id}`);
  }

}
