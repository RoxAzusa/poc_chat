import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { LoginRequest } from '../../models/loginRequest.model';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void { }

  public submit(): void {
    const loginRequest: LoginRequest = {
      email: this.email,
      password: this.password
    }

    this.authService.login(loginRequest).subscribe((user: User) => {
      localStorage.setItem('user', JSON.stringify(user));
      this.router.navigate(['/chat'])
    })
  }
}
