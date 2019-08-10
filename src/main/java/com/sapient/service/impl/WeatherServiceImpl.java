package com.sapient.service.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class WeatherServiceImpl {
	public String getData(String cityName) {
		String url = "https://samples.openweathermap.org/data/2.5/forecast?q=London,us&mode=xml&appid=b6907d289e10d714a6e88b30761fae22";
		
		UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl("https://samples.openweathermap.org/data/2.5/forecast")

                .queryParam("appid", "b6907d289e10d714a6e88b30761fae22")

                .queryParam("q", cityName);
		
		RestTemplate restTemplate = new RestTemplate();
		String res = restTemplate.getForObject(queryBuilder.toUriString(), String.class);
		System.out.println(res);
		return res;
		
	}
}
