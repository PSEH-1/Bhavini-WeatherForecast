package com.sapient.service.impl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@Component
public class WeatherServiceImpl {
	public String getData(String cityName) {

		UriComponentsBuilder queryBuilder = UriComponentsBuilder
				.fromHttpUrl("https://samples.openweathermap.org/data/2.5/forecast")

				.queryParam("appid", "b6907d289e10d714a6e88b30761fae22")

				.queryParam("q", cityName);

		RestTemplate restTemplate = new RestTemplate();
		String res = restTemplate.getForObject(queryBuilder.toUriString(), String.class);
		System.out.println(res);
		return printWeatherData(res);

	}
	
	private String printWeatherData(String res) {
		String output = null;
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonObj = (JSONObject) parser.parse(res);
			JSONArray lists = (JSONArray) jsonObj.get("list");
			JSONObject listObj = (JSONObject) lists.get(0);
			JSONObject mainObj = (JSONObject) listObj.get("main");
			double temp = Double.parseDouble(mainObj.get("temp").toString());
			if(temp > 40.0) {
				output = "Use Sunscreen!!";
			}
			
			JSONArray weatherObjList = (JSONArray) listObj.get("weather");
			JSONObject weatherObj = (JSONObject) weatherObjList.get(0);
			String mainWeather = weatherObj.get("main").toString();
			if(mainWeather.equalsIgnoreCase("Rain"))
				output = "Carry Umbrella!!";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
		
	}
}
