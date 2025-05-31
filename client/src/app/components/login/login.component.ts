import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { UserLogin } from '../../interfaces/userLogin';
import { UsersService } from '../../services/users.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent  {


username:string =""
password:string = ""

constructor(
  private userService:UsersService,
  private route:Router,
  private storageService:StorageService
){
}
  
 

  addUser(){

    if(this.username === "" || this.password === ""){
      window.alert("campos vacios")
    }else{

      
      
      const userLongin: UserLogin ={
        username:this.username,
        password:this.password
      }
      
      
      this.userService.login(userLongin).subscribe(
        {
          next:(data) =>{
            this.storageService.setStorage(data)
            
            this.route.navigate(["/home"])
          },
          error:(event :HttpErrorResponse)=>{
           
            window.alert(event.error)
          },
          complete:()=>{
            window.alert("logeado con exito")
          }
        }
      )


    }

    
  }
}
