package com.hotelreview.api.controller;

import com.hotelreview.api.dto.HotelDto;
import com.hotelreview.api.dto.HotelResponse;
import com.hotelreview.api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class HotelController {
    private HotelService hotelService;
    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }
    @GetMapping("hotel")
    public ResponseEntity<HotelResponse> getHotels(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<> (hotelService.getAllHotel(pageNo, pageSize), HttpStatus.OK);
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
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}
