package com.hotelreview.api.service.impl;

import com.hotelreview.api.dto.HotelDto;
import com.hotelreview.api.models.Hotel;
import com.hotelreview.api.repository.HotelRepository;
import com.hotelreview.api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    private HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        hotel.setCity(hotelDto.getCity());

        Hotel newHotel = hotelRepository.save(hotel);

        HotelDto hotelResponse = new HotelDto();
        hotelResponse.setId(newHotel.getId());
        hotelResponse.setName(newHotel.getName());
        hotelResponse.setCity(newHotel.getCity());
        return hotelResponse;
    }
}
