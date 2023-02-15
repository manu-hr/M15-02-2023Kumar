import { Component } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  isLoggedIn:boolean = false;

  constructor(private userService:UserService) {}

  ngOnInit():void {
    this.userService.isLoggedIn.subscribe((data)=> this.isLoggedIn = data);
  }

  logout():void {
    this.userService.clearToken();
  }

}
