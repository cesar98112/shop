import { Component } from '@angular/core';
import { UsersService } from '../../services/users.service';
import { UserRequest } from '../../interfaces/userRequest';
import { error } from 'console';
import { Router } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  username:string =""
  name:string=""
  lastName:string=""
  email:string=""
  password:string=""
  passwordRepeat:String=""


  constructor(
    private service:UsersService,
    private route:Router
  ){

  }

  register(){
    

    if(this.username == "" || this.name == "" || this.lastName == "" || this.email == "" || this.password == "" || this.passwordRepeat == ""){
      window.alert("Es necesario rellenar todo los campos")

    }else if(this.password !== this.passwordRepeat){
      window.alert("las contraseñas tiene que coincidir")
    }
    else{  
      
      const user:UserRequest = {
        userName:this.username,
        name:this.name,
        password:this.password,
        email:this.email,
        lastName:this.lastName
      }

      this.service.register(user).subscribe(data =>{
        window.alert("usuario creado con exito")
        this.route.navigate(["/login"])
        
      },error =>{
        window.alert("error al crear el usuario")
      })

    }

  }

}
