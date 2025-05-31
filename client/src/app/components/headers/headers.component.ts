import { Component, OnInit } from '@angular/core';
import { UserResponse } from '../../interfaces/userRespones';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-headers',
  standalone: false,
  templateUrl: './headers.component.html',
  styleUrl: './headers.component.css'
})
export class HeadersComponent implements OnInit{
   user:UserResponse |null = null

   constructor(private service:StorageService){
    
   }
  ngOnInit(): void {
     this.user = this.service.getStorage()

    
  }






}
