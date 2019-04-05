import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ServiceWorkerModule} from '@angular/service-worker';
import {environment} from '../environments/environment';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule, MatCheckboxModule, MatInputModule, MatPaginatorModule, MatSnackBarModule, MatTableModule} from "@angular/material";
import {LoginComponent} from './auth/login/login.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {LockerModule} from "angular-safeguard";
import {EntryListComponent} from './entry/entry-list/entry-list.component';
import {AuthInterceptor} from "./auth/auth.interceptor";
import {EntryEditComponent} from './entry/entry-edit/entry-edit.component';
import {EntryItemComponent} from './entry/entry-item/entry-item.component';
import {FileUploadModule} from "ng2-file-upload";
import {EditorModule} from "@tinymce/tinymce-angular";
import {SafePipe} from "./util/safepipe";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    EntryListComponent,
    EntryEditComponent,
    EntryItemComponent,

    // Pipes
    SafePipe,
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

    FileUploadModule,
    EditorModule,

    ServiceWorkerModule.register('ngsw-worker.js', {enabled: environment.production})
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
