import { Component, EventEmitter, Input, Output } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { ReviewDialogComponent } from '../review-dialog/review-dialog.component';
import { Review } from '../models/review.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-review-card',
  templateUrl: './review-card.component.html',
  styleUrls: ['./review-card.component.css']
})
export class ReviewCardComponent {

  @Input()
  review!: Review;

  @Output()
  deleteReviewId = new EventEmitter<number>;

  isLoggedIn:boolean = false;

  constructor(public dialog: MatDialog, private userService:UserService) {}

  ngOnInit() {
    this.userService.isLoggedIn.subscribe((data) => this.isLoggedIn = data)
  }

  clicked() {
    console.log("Hi");
    this.dialog.open(ReviewDialogComponent,{
      data : this.review
    });
  }

  delete() {
    this.deleteReviewId.emit(this.review.reviewId);
  }

  
}
