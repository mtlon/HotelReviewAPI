package com.hotelreview.api.service;

import com.hotelreview.api.dto.HotelDto;

import java.util.List;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto);
    List<HotelDto> getAllHotel();
    HotelDto getById(int id);
    HotelDto updateHotel(HotelDto hotelDto, int id);

}
