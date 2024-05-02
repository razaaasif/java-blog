import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { JWTResponseModel } from 'src/app/shared/model/JWTResponse.model';
import { LoginModel } from 'src/app/shared/model/login.model';
import { AuthServiceService } from 'src/app/shared/services/auth-service.service';
import { UserLoginService } from 'src/app/shared/services/user-login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup;
  loginModel!: LoginModel;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthServiceService,
    private messageService: MessageService,
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required],
    });
  }

  ngOnInit(): void {}
  login() {
    this.loginModel = new LoginModel(
      this.loginForm.get('username')?.value,
      this.loginForm.get('password')?.value
    );
    console.log(JSON.stringify(this.loginModel));
    if (!this.loginForm.valid) {
      return;
    }

    this.authService.login(this.loginModel).subscribe(
      (token :JWTResponseModel) => {
        if (token.success) {
            console.log(token);
            this.messageService.add({
              severity: 'success',
              summary: 'Logged in successfully.',
            });
          this.router.navigateByUrl('/home');
        }
      },
      (error) => {
        console.error(error);
        throw error;
      }
    );
  }
}
