package com.challege.javatask.controller;

import com.challege.javatask.dto.ReviewDTO;
import com.challege.javatask.exceptions.DocumentNotFoundException;
import com.challege.javatask.exceptions.InvalidRequest;
import com.challege.javatask.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/v1/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("{id}")
    public ResponseEntity<?> getReview(@PathVariable Long id) throws DocumentNotFoundException {
        return new ResponseEntity<>(reviewService.findReviewById(id), HttpStatus.OK );
    }

    @GetMapping
    public ResponseEntity<?> getAllReviews() {
        return new ResponseEntity<>(reviewService.findAllReviews(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO review) throws InvalidRequest {
        return new ResponseEntity<>(reviewService.addReview(review), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.deleteReview(id), HttpStatus.OK);
    }

}
