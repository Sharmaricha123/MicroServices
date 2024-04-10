package com.user.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.converters.Auto;
import com.user.entities.Hotel;
import com.user.entities.Rating;
import com.user.entities.User;
import com.user.exception.ResourceNotFoundException;
import com.user.external.services.HotelService;
import com.user.repositories.UserRespository;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRespository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

//	@Autowired
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {

		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {

		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("user wity given id is not found on server !!" + userId));

//		fetch rating of the above user from RATING-SERVICE
//		http://localhost:8083/ratings/user/842e21d1-0fbe-4816-aee6-18f90d39976a
		Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + user.getUserId(),
				Rating[].class);

		logger.info("{}", ratingOfUser);
		List<Rating> ratings = Arrays.stream(ratingOfUser).collect(Collectors.toList());

		List<Rating> ratingList = ratings.stream().map(rating -> {

//			api call to hotel service to get the hotel
//			http://localhost:8082/hotels/9b790ed4-f1c5-428f-893d-1210e37d1a0b
			
//----------------Using rest template---------------------------------
//		ResponseEntity<Hotel> forEntity=	restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//		Hotel hotel=forEntity.getBody();
//	    logger.info("response status code: {}",forEntity.getStatusCode());

//--------------------------------------------------------------------
			
			
//----------------------------using feign client----------------------

			Hotel hotel = hotelService.getHotel(rating.getHotelId());
//--------------------------------------------------------------------
			rating.setHotel(hotel);
			return rating;

		}).collect(Collectors.toList());

		user.setRatings(ratingList);

		return user;
	}

}
