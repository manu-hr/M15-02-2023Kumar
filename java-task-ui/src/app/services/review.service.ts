import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from '../models/review.model';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  
  baseurl:string = 'http://localhost:8080/service/v1/review';
  authToken = localStorage.getItem("token");

  constructor(private http:HttpClient) { }

  getAllReviews():Observable<any> {
    return this.http.get(this.baseurl);
  }

  getReviewById(id:number):Observable<any> {
    return this.http.get(`${this.baseurl}/${id}`)
  }

  addReview(review:Review):Observable<any>{
    return this.http.post(this.baseurl,review, {
      headers : new HttpHeaders({
        'Authorization': 'Bearer ' + this.authToken
      })
    })
  }

  deleteReview(id:number):Observable<any> {
    return this.http.delete(`${this.baseurl}/${id}`,{
      headers : new HttpHeaders({
        'Authorization': 'Bearer ' + this.authToken
      })
    })
  }
 
}
