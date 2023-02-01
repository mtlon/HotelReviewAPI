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
    public ResponseEntity <List<HotelDto>> getHotels() {
        return new ResponseEntity<>(hotelService.getAllHotel(), HttpStatus.OK);
    }
    @GetMapping("hotel/{id}")
    public ResponseEntity<HotelDto> hotelDetail(@PathVariable int id) {
        return ResponseEntity.ok(hotelService.getById(id));
    }
    @PostMapping("hotel/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HotelDto> createPokemon(@RequestBody HotelDto hotelDto) {
        return new ResponseEntity<>(hotelService.createHotel(hotelDto), HttpStatus.CREATED);
    }
    @PutMapping("hotel/{id}/update")
    public ResponseEntity<HotelDto> updateHotel(@RequestBody HotelDto hotelDto, @PathVariable("id") int hotelId) {
        HotelDto response = hotelService.updateHotel(hotelDto, hotelId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("hotel/{id}/delete")
    public ResponseEntity<String> deleteHotel(@PathVariable("id") int hotelId) {
        hotelService.deleteById(hotelId);
        return new ResponseEntity<>("Deleted successful", HttpStatus.OK);
    }
}
