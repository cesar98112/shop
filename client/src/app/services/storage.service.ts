import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { UserResponse } from '../interfaces/userRespones';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  userAux:UserResponse | null = null
  private userSubjet = new BehaviorSubject<UserResponse|null>(this.userAux)
  user$ = this.userSubjet.asObservable();
  constructor(@Inject(PLATFORM_ID) private platformId: Object ) {
    if (typeof window !== 'undefined' && window.localStorage) {
      const user = localStorage.getItem("user");
      if(user){
        let users = JSON.parse(user) as UserResponse
        this.userSubjet.next(users)
      }
      
    }
   }

  setStorage(user:UserResponse | null){
     localStorage.setItem("user",JSON.stringify(user))
      const users = localStorage.getItem("user")
      if(users){
        this.userAux = JSON.parse(users) as UserResponse
      } 
     this.userSubjet.next(user)
  }

  getStorage(){

    if(isPlatformBrowser(this.platformId)){
        let stringUser = localStorage.getItem("user")
        if(stringUser){
          return JSON.parse(stringUser) as UserResponse
        }
       
    }
    return null

    
  }




}
