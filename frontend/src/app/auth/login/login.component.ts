import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  isLoggedIn: boolean = false;

  username: string;
  password: string;


  constructor(private authService: AuthService,
              private snackbar: MatSnackBar) {
  }

  ngOnInit() {
    this.checkLogin();
  }

  onLogin() {
    this.authService
      .login(this.username, this.password)
      .then(() => {
        this.checkLogin();
      }).catch(err => {
      this.checkLogin();
      console.log("LOGIN-ERR", err);
      //this.snackbar.open('Could not login. Reason: ' + err.statusText);
    })
  }

  checkLogin() {
    this.authService
      .checkLoggedIn()
      .then(isLoggedIn => {
        this.isLoggedIn = isLoggedIn;
        if (isLoggedIn) {
          this.snackbar.open('You are logged in');
        } else {
          this.snackbar.open('You are logged out');
        }
      });
  }

  onLogout() {
    this.authService.logout();
    this.checkLogin();
  }
}
