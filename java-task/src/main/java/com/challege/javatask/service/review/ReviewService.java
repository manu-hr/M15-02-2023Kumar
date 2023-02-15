package com.challege.javatask.service.review;

import com.challege.javatask.domain.Review;
import com.challege.javatask.dto.ReviewDTO;
import com.challege.javatask.exceptions.DocumentNotFoundException;
import com.challege.javatask.exceptions.InvalidRequest;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<Review> findAllReviews();
    Review findReviewById(Long id) throws DocumentNotFoundException;
    Map addReview(ReviewDTO review) throws InvalidRequest;
    Map deleteReview(Long id);
}
