import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../environments/environment';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
  MatButtonModule,
  MatCheckboxModule,
  MatInputModule,
  MatPaginator, MatPaginatorModule,
  MatSnackBarModule,
  MatTable,
  MatTableModule
} from "@angular/material";
import { LoginComponent } from './auth/login/login.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {LockerModule} from "angular-safeguard";
import { EntryListComponent } from './entry/entry-list/entry-list.component';
import {AuthInterceptor} from "./auth/auth.interceptor";
import { EntryEditComponent } from './entry/entry-edit/entry-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    EntryListComponent,
    EntryEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,

    LockerModule,

    BrowserAnimationsModule,
    FormsModule,

    MatButtonModule,
    MatCheckboxModule,
    MatInputModule,
    MatSnackBarModule,
    MatTableModule,
    MatPaginatorModule,

    ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production })
  ],
  providers: [  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
