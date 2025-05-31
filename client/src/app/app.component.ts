import { Component, Inject, Input, OnInit, PLATFORM_ID } from '@angular/core';
import { UserResponse } from './interfaces/userRespones';


import { isPlatformBrowser } from '@angular/common';
import { StorageService } from './services/storage.service';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  logiRoute:boolean = false
  user: UserResponse| null = null

  constructor(
    private service:StorageService,
    private route :Router
  ){}

  

  ngOnInit(): void {
     this.route.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: any) => {
        let currentRoute:string = event.urlAfterRedirects;
        
        if(currentRoute != "/login" && currentRoute != "/register"){
          this.logiRoute = true
        }else{
          this.logiRoute = false
        }
       
      });
    
    
  }

  
  

  title = 'client';
}
