import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url:string = 'http://localhost:8080/service/v1/token';
  isLoggedIn = new BehaviorSubject<boolean>(localStorage.getItem('isLoggedIn') == "true" ?? false);

  constructor(private http: HttpClient) { }

  getToken(user:any):Observable<any> {
    return this.http.post(this.url,user);
  }

  clearToken() {
    this.isLoggedIn.next(false);
    localStorage.clear();
  }


}
