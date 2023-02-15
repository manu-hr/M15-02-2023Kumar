import { Component } from '@angular/core';
import { Review } from '../models/review.model';
import { ReviewService } from '../services/review.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent {
  review:Review = {
    reviewDescription: '',
    rating: 0,
    reviewTitle: '',
    reviewId: undefined
  }

  constructor(private reviewService:ReviewService, private router: Router, private _snackBar: MatSnackBar) {}

  submit():void {
    console.log(this.review);
    this.reviewService.addReview(this.review).subscribe({
      next : (data) => {
        console.log(data);
        this.router.navigate([""]);        
      },
      error : (err) => {
        console.log(err);
        this._snackBar.open("Something went wrong!","Close")
      }
    })
    
  }
}
