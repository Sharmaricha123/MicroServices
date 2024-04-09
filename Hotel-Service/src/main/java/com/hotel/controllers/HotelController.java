package com.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entities.Hotel;
import com.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		Hotel savedHotel=hotelService.createHotel(hotel);
		return new ResponseEntity<Hotel>(savedHotel,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> get(@PathVariable String id)
	{
		Hotel hotel=hotelService.get(id);
		return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAll(){
		List<Hotel> allHotel=hotelService.getAll();
		return new ResponseEntity<List<Hotel>>(allHotel,HttpStatus.OK);
	}

}
