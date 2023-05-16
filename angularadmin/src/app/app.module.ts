import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { HttpClientModule } from '@angular/common/http'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrderListComponent } from './order-list/order-list.component';
import { HomeComponent } from './home/home.component';
import { IdDialogComponent } from './id-dialog/id-dialog.component';
import { MatDialogModule, MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import {MatMenuModule} from '@angular/material/menu';
import jwt_decode from 'jwt-decode';
import { LoginComponent } from './login/login.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { FormsModule } from '@angular/forms';
import { JWT_OPTIONS, JwtHelperService, JwtModule } from '@auth0/angular-jwt';



@NgModule({
  declarations: [
    AppComponent,
    OrderListComponent,
    HomeComponent,
    IdDialogComponent,
    LoginComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule,
    MatCardModule,
    MatInputModule,
    MatProgressSpinnerModule,
    MatMenuModule,
    MatFormFieldModule,
    MatCheckboxModule,
    FormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem('access_token');
        },
      },
    }),
  ],
  providers: [JwtHelperService],
  bootstrap: [AppComponent],
  
})
export class AppModule { }
