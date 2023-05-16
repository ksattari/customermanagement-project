import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:7073';
  private loggedIn = new BehaviorSubject<boolean>(false);

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor(private http: HttpClient) {
    // Check if the user is already logged in on initialization
    this.checkAuthStatus();
  }
    

  login(username: string, password: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/api/login`, { username, password })
    .pipe(
      tap((res: any) => {
        localStorage.setItem('access_token', res.access_token);
        this.loggedIn.next(true);
      })
    );
  }

  private checkAuthStatus() {
    const token = localStorage.getItem('access_token');
    if (token) {
      // Verify token validity on the server-side if needed
      this.loggedIn.next(true);
    }
  }

  logout() {
    localStorage.removeItem('access_token');
    this.loggedIn.next(false);
  }
}
