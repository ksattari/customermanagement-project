import { Component, HostListener, ViewChild } from '@angular/core';
import { AuthService } from './service/auth.service';
import { Router } from '@angular/router';
//import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  //@ViewChild('sidenav') sidenav!: MatSidenav;
  title = 'angularadmin';
  isLoggedIn = false;


  constructor(private authService: AuthService, private router: Router) {
    this.authService.isLoggedIn.subscribe((loggedIn: boolean) => {
      this.isLoggedIn = loggedIn;
    });
  }

  @HostListener('window:beforeunload', ['$event'])
  handleBeforeUnload(event: Event) {
    // No action is performed here
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
  

ngOnDestroy() {
      this.authService.logout(); // Call the logout method from AuthService
}
}
