import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../models/loginRequest.model';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private htppClient: HttpClient) { }

  public login(loginRequest: LoginRequest): Observable<User> {
    return this.htppClient.post<User>('/api/auth/login', loginRequest);
  }
}
