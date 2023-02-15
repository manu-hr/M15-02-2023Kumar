import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username:string = '';
  password:string = '';

  constructor(private userService:UserService, private route:Router, private _snackBar: MatSnackBar) {}

  submit() {
    console.log(this.username, this.password);
    let user = {
      "username" : this.username,
      "password" : this.password
    }

    this.userService.getToken(user).subscribe({
      next: (resp) => {
        console.log(resp);
        this.userService.isLoggedIn.next(true);
        localStorage.setItem("token",resp.token);
        localStorage.setItem("isLoggedIn","true");
        this.route.navigateByUrl("");
      },
      error : (err) => {
        console.log(err);
        if(err.status == 404) {
             this._snackBar.open("No User Found!", "Close")  
        } else {
          this._snackBar.open("Something Went Wrong!", "Close")  

        }
      }
    })
    
  }
  
}
