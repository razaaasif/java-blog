import { Component, OnInit } from '@angular/core';
import { UserLoginService } from '../shared/services/auth/user-login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  constructor(public userLoginService: UserLoginService , private router: Router) { }

  ngOnInit(): void { }

  public logout(): void {
    this.userLoginService.logout();
    this.router.navigateByUrl('/login');
  }
}
