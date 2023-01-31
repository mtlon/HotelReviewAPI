package com.hotelreview.api.service.impl;

import com.hotelreview.api.dto.HotelDto;
import com.hotelreview.api.models.Hotel;
import com.hotelreview.api.repository.HotelRepository;
import com.hotelreview.api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<HotelDto> getAllHotel() {
        List<Hotel> hotel = hotelRepository.findAll();
        return hotel.stream().map(h -> mapToDto(h)).collect(Collectors.toList());
    }

    public HotelDto mapToDto(Hotel hotel) {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());
        hotelDto.setCity(hotel.getCity());
        return hotelDto;
    }
    public Hotel mapToEntity(HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        hotel.setCity(hotelDto.getCity());
        return hotel;
    }

}
