package com.hotelreview.api.service.impl;

import com.hotelreview.api.dto.ReviewDto;
import com.hotelreview.api.exceptions.HotelNotFoundException;
import com.hotelreview.api.exceptions.ReviewNotFoundException;
import com.hotelreview.api.models.Hotel;
import com.hotelreview.api.models.Review;
import com.hotelreview.api.repository.HotelRepository;
import com.hotelreview.api.repository.ReviewRepository;
import com.hotelreview.api.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private HotelRepository hotelRepository;
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(HotelRepository hotelRepository, ReviewRepository reviewRepository) {
        this.hotelRepository = hotelRepository;
        this.reviewRepository = reviewRepository;
    }
    @Override
    public ReviewDto creteReview(int hotelId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);

        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException
                        ("Hotel with associated review not found"));

        review.setHotel(hotel);
        Review newReview = reviewRepository.save(review);

        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewsByHotelId(int id) {
        List<Review> reviews = reviewRepository.findByHotelId(id);
        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(int hotelId, int reviewId) {
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new HotelNotFoundException
                        ("Hotel with associated review not found"));

        Review review = reviewRepository
                .findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException
                        ("This review does not belong to a hotel"));

        if(review.getHotel().getId() != hotel.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a hotel");
        }

        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto, int hotelId, int reviewId) {
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new HotelNotFoundException
                        ("Hotel with associated review not found"));
        Review review = reviewRepository
                .findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException
                        ("This review does not belong to a hotel"));
        if(review.getHotel().getId() != hotel.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a hotel");
        }
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        Review updatedReview = reviewRepository.save(review);

        return mapToDto(review);
    }

    @Override
    public void deleteReview(int hotelId, int reviewId) {
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new HotelNotFoundException
                        ("Hotel with associated review not found"));
        Review review = reviewRepository
                .findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException
                        ("This review does not belong to a hotel"));
        if(review.getHotel().getId() != hotel.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a hotel");
        }

        reviewRepository.delete(review);
    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }
    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }
}
