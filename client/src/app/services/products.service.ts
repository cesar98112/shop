import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../interfaces/product';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private  APIurl:string;

  constructor(private http:HttpClient) { 
    this.APIurl  = "http://localhost:8080"
  }
  
  getAllProduct():Observable<Product[]>{
    return this.http.get<Product[]>(`${this.APIurl}/api/product/getAll`);
  }

}
