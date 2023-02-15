import { Component } from '@angular/core';
import { ReviewService } from '../services/review.service';
import { Review } from '../models/review.model';

@Component({
  selector: 'app-review-home',
  templateUrl: './review-home.component.html',
  styleUrls: ['./review-home.component.css']
})
export class ReviewHomeComponent {
  constructor(private reviewService:ReviewService) {}

  reviews:Review[] = [];

  ngOnInit():void {
    this.getAllReviews();
  }

  getAllReviews():void {
    this.reviewService.getAllReviews().subscribe({
      next : (data) => {
        console.log(data);
        this.reviews = data;
      }, 
      error : (err) => {
        console.log(err);
      }
    })
  }

  deleteReview(id:any):void {
    console.log(id);

    this.reviewService.deleteReview(id).subscribe({
      next : (resp) =>{
        this.reviews = resp.data;
      },
      error : (err) =>{
        console.log(err);
        
      }
    })
    
  }
}
