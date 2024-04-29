import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterModel } from 'src/app/shared/model/register.model';
import { AuthServiceService } from 'src/app/shared/services/auth/auth-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  private register!: RegisterModel;
  constructor(
    private formBuilder: FormBuilder,
    private authServiceService: AuthServiceService
  ) {
    this.registerForm = this.formBuilder.group({
      username: [null, Validators.required],
      name: [null, Validators.required],
      email: [null, Validators.email],
      password: [null, Validators.required],
      confirmPassword: [null, Validators.required],
    });
  }
  ngOnInit(): void {}
  public submit() {
    this.register = new RegisterModel(
      this.registerForm.get('username')?.value,
      this.registerForm.get('name')?.value,
      this.registerForm.get('email')?.value,
      this.registerForm.get('password')?.value
    );
    this.authServiceService.register(this.register).subscribe(
      (data) => {
        console.log(data);
      },
      (error) => {
        throw new Error(error.message);
      }
    );
  }
}
