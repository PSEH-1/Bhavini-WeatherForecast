package com.sapient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.service.impl.WeatherServiceImpl;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	@Autowired
	WeatherServiceImpl weatherServiceImpl;

//	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@GetMapping
	public ResponseEntity<Object> getWeatherData(
			@RequestParam(value = "city", defaultValue = "Bangalore") String name) {
		System.out.println(name);
		
		return new ResponseEntity<>(weatherServiceImpl.getData(name), HttpStatus.OK);
	}
}
