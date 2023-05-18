import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthService } from '../service/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  hide = true;
  username: string;
  password: string;
  rememberMe: boolean;
  errorMessage: string;
  error: boolean = false
  private subscription: Subscription;

  constructor(
    private authService: AuthService,
    private router: Router,
    private jwtHelper: JwtHelperService
  ) {
    this.subscription = this.authService.isLoggedIn.subscribe((isLoggedIn: boolean) => {
    if (isLoggedIn) {
      this.router.navigate(['/']);
    }
  });
 } 

  onSubmit() {

    if (!this.username || !this.password) {
      // If username or password is empty, clear the fields and return
      this.username = '';
      this.password = '';
      this.errorMessage = "username and password cannot be empty.";
      this.error = true;
      return;
    }
    this.authService.login(this.username, this.password)
      .subscribe({
        next: data => {
          localStorage.setItem('access_token', data.access_token);
          this.router.navigate(['/']);
        },
        error: error => {
          this.error = true;
          console.log(error);
          this.errorMessage = error.error ? 'An error occurred during login.' : "Incorrect username or password. Please try again.";
          this.username = ''; // Clear username field
          this.password = ''; // Clear password field
        }
      });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
