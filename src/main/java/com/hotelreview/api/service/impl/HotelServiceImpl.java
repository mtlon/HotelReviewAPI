package com.hotelreview.api.service.impl;

import com.hotelreview.api.dto.HotelDto;
import com.hotelreview.api.exceptions.HotelNotFoundException;
import com.hotelreview.api.models.Hotel;
import com.hotelreview.api.repository.HotelRepository;
import com.hotelreview.api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Override
    public HotelDto getById(@PathVariable int id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Hotel not found!"));
        return mapToDto(hotel);
    }

    @Override
    public HotelDto updateHotel(@RequestBody HotelDto hotelDto, @PathVariable int id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Hotel not could not be updated"));
        hotel.setName(hotelDto.getName());
        hotel.setCity(hotelDto.getCity());
        Hotel updatedHotel = hotelRepository.save(hotel);
        return mapToDto(updatedHotel);

    }
    @Override
    public void deleteById(int id) {
        HotelDto hotel = getById(id);
        hotelRepository.deleteById(id);
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
