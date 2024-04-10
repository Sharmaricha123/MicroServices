package com.user.external.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

}
