package com.hotelreview.api.repository;

import com.hotelreview.api.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByHotelId(int hotelId);
}
