import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  constructor(private authService: AuthService,
              private snackbar: MatSnackBar) {
  }

  ngOnInit() {
  }

  onLogin() {
    this.authService
      .login(this.username, this.password)
      .then(() => {
        this.snackbar.open('You are logged in');
      }).catch(err => {
      this.snackbar.open('Could not login. Reason: ' + err.statusText);
      console.log("LOGIN", err);
    })
  }
}
