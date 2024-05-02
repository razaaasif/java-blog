import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { PrimeNgModule } from './app.primeng.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LocalStorageService, NgxWebstorageModule } from 'ngx-webstorage';
import { HomePageComponent } from './home-page/home-page.component';
import { EditorComponent, EditorModule } from '@tinymce/tinymce-angular';
import { AddPostComponent } from './add-post/add-post.component';
import { BlogInterceptor } from './shared/interceptor/blog.interceptor';
import { PostDetailComponent } from './home-page/post-detail/post-detail.component';
import { TruncatePipe } from './shared/pipe/truncate.pipe';
import { CommonModule } from '@angular/common';
import { AuthGuard } from './shared/gaurd/auth.guard';
import { BaseUrlInterceptor } from './shared/interceptor/base-url.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    RegisterComponent,
    LoginComponent,
    HomePageComponent,
    AddPostComponent,
    TruncatePipe,
    PostDetailComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    PrimeNgModule,
    BrowserAnimationsModule,
    NgxWebstorageModule.forRoot(),
    EditorModule,
    CommonModule,
    RouterModule.forRoot([
      { path: 'posts', component: HomePageComponent },

      { path: 'register', component: RegisterComponent },
      { path: 'login', component: LoginComponent },
      {
        path: 'add-post',
        component: AddPostComponent,
        canActivate: [AuthGuard],
      },
      { path: 'posts/:id', component: PostDetailComponent },

      { path: '**', redirectTo: '/posts' },
    ]),
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BlogInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: BaseUrlInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
  exports: [EditorComponent],
})
export class AppModule {}
