import { isPlatformBrowser } from '@angular/common';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Router } from '@angular/router';
import { escape } from 'querystring';
import { catchError, Observable, throwError } from 'rxjs';
import { UserResponse } from '../interfaces/userRespones';

@Injectable()
export class AddTokenInterceptor implements HttpInterceptor{
  constructor(
    private route:Router,
    @Inject(PLATFORM_ID) private platformId: Object 
  ){}
  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    
    
    if(isPlatformBrowser(this.platformId)){
        
        const userStorage = localStorage.getItem("user")

        let user: UserResponse| null = null


        if(userStorage){
          user = JSON.parse(userStorage) as UserResponse
          
        }
        if(user){
      
        req = req.clone({setHeaders:{Authorization:`Bearer ${user.token}`}})
      
        }
    }
    
    

    return next.handle(req).pipe(catchError((error:HttpErrorResponse)=>{
      
      if(error.status === 401){
        
        this.route.navigate(["/login"])
      }
      return throwError(()=>error)
    }))
  }
  
}

