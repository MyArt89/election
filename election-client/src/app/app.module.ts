import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HttpService} from './HttpService';
import {HttpClient, HttpClientModule} from '@angular/common/http';

// @ts-ignore
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [
    HttpClient,
    HttpService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
