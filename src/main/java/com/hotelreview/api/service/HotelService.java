package com.hotelreview.api.service;

import com.hotelreview.api.dto.HotelDto;
import com.hotelreview.api.dto.HotelResponse;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto);
    HotelResponse getAllHotel(int pageNo, int pageSize);
    HotelDto getById(int id);
    HotelDto updateHotel(HotelDto hotelDto, int id);
    void deleteById(int id);

}
