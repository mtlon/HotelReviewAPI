package com.hotelreview.api.controller;

import com.hotelreview.api.dto.ReviewDto;
import com.hotelreview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("hotel/{hotelId}/review")
    public List<ReviewDto> getReviewsByHotelId(@PathVariable("hotelId") int hotelId) {
        return reviewService.getReviewsByHotelId(hotelId);
    }
    @GetMapping("hotel/{hotelId}/review/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable ("hotelId") int hotelId, @PathVariable("id") int reviewId) {
        ReviewDto reviewDto = reviewService.getReviewById(hotelId,reviewId);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }
    @PostMapping("hotel/{hotelId}/review")
    public ResponseEntity<ReviewDto> createReview(@PathVariable("hotelId") int hotelId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.creteReview(hotelId, reviewDto), HttpStatus.CREATED);
    }
}
