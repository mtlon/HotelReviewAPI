package com.hotelreview.api.controller;

import com.hotelreview.api.dto.HotelDto;
import com.hotelreview.api.models.Hotel;
import com.hotelreview.api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class HotelController {
    private HotelService hotelService;
    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }
    @GetMapping("hotel")
    public ResponseEntity<List<Hotel>> getHotels() {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel(1,"Caesars Palace","Las Vegas"));
        hotels.add(new Hotel(2,"Icehotel Sweden","Jukkasjarv"));
        hotels.add(new Hotel(3,"The Plaza","New York"));
        return ResponseEntity.ok(hotels);
    }
    @GetMapping("hotel/{id}")
    public Hotel hotelDetail(@PathVariable int id) {
        return new Hotel(id, "Hilton", "Warsaw");
    }
    @PostMapping("hotel/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HotelDto> createPokemon(@RequestBody HotelDto hotelDto) {
        return new ResponseEntity<>(hotelService.createHotel(hotelDto), HttpStatus.CREATED);
    }
    @PutMapping("hotel/{id}/update")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable("id") int hotelId) {
        System.out.println(hotel.getName());
        System.out.println(hotel.getCity());
        return ResponseEntity.ok(hotel);
    }
    @DeleteMapping("hotel/{id}/delete")
    public ResponseEntity<String> deleteHotel(@PathVariable("id") int hotelId) {
        System.out.println(hotelId);
        return ResponseEntity.ok("Deleted successfully");
    }
}
