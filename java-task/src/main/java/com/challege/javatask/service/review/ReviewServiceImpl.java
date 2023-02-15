package com.challege.javatask.service.review;


import com.challege.javatask.domain.Review;
import com.challege.javatask.dto.ReviewDTO;
import com.challege.javatask.exceptions.DocumentNotFoundException;
import com.challege.javatask.exceptions.InvalidRequest;
import com.challege.javatask.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findReviewById(Long id) throws DocumentNotFoundException {
        Optional<Review> review = reviewRepository.findById(id);
        return review.orElse(null);
    }

    @Override
    public Map addReview(ReviewDTO review) throws InvalidRequest {
        if(review.getReviewTitle().equals("") || review.getRating() > 5)
            throw new InvalidRequest();

        Review reviewObj = new Review();
        reviewObj.setReviewTitle(review.getReviewTitle());
        reviewObj.setReviewDescription(review.getReviewDescription());
        reviewObj.setRating(review.getRating());
        Map<String,Object> map = new HashMap<>();
        map.put("message","Review Added");
        map.put("data", reviewRepository.save(reviewObj));
        return map;
    }

    @Override
    public Map deleteReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()) {
            reviewRepository.deleteById(id);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("message","Review Deleted");
        map.put("data", reviewRepository.findAll());

        return map;
    }
}
