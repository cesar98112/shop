import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserLogin } from '../interfaces/userLogin';
import { Observable } from 'rxjs';
import { UserRequest } from '../interfaces/userRequest';
import { UserResponse } from '../interfaces/userRespones';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private apiUrl:string;

  constructor(private http:HttpClient) {
    this.apiUrl ="http://localhost:8080"
   }

   login(user:UserLogin):Observable<UserResponse>{
    return this.http.post<UserResponse>(`${this.apiUrl}/api/auth/login`,user)
   }

   register(user:UserRequest):Observable<UserResponse>{
    return this.http.post<UserResponse>(`${this.apiUrl}/api/auth/register`,user)
   }
   
}
