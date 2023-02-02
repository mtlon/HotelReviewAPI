package com.hotelreview.api.service;

import com.hotelreview.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto creteReview (int hotelId, ReviewDto reviewDto);
    List<ReviewDto> getReviewsByHotelId(int id);
}
