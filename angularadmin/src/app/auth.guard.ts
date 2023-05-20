import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot, UrlSegment, UrlTree } from '@angular/router';
import { Observable, map, take } from 'rxjs';
import { AuthService } from './service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this.authService.isLoggedIn.pipe(
      take(1), // Take only the first emitted value
      map((loggedIn: boolean) => {
        if (loggedIn) {
          console.log('AuthGuard: User is logged in');
          return true;
        } else {
          console.log('AuthGuard: User is not logged in');
          this.router.navigate(['/login']);
          return false;
        }
      })
    );
  }
}