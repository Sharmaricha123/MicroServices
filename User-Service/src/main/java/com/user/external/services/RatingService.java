package com.user.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.user.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	@PostMapping("/ratings")
	public ResponseEntity<Rating> create(Rating rating);
	
	
	@PutMapping("/rating/{ratingId}")
	public ResponseEntity<Rating> updareRating(@PathVariable String ratingId,Rating rating);
	
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);

}
