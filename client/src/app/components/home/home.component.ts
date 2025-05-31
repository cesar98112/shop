import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { Router } from '@angular/router';
import { ProductsService } from '../../services/products.service';
import { isPlatformBrowser } from '@angular/common';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  file:File|null = null
  constructor(
    private route:Router,
    private serve:ProductsService, 
    private storageServer:StorageService,
    @Inject(PLATFORM_ID) private platformId: Object 
  ){

  }
  ngOnInit(): void {
    this.getProduct()
    
    
    
  }
  getProduct(){
    this.serve.getAllProduct().subscribe(data =>{
      console.log(data)
    })
  }

  clear(){
    
    localStorage.clear()
    this.storageServer.setStorage(null)
    this.route.navigate(["/login"])
  }
}
