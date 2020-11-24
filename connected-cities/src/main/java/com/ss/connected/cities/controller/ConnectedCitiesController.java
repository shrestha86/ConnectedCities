package com.ss.connected.cities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.connected.cities.service.ConnectedCitiesService;

@RestController
public class ConnectedCitiesController {
	
	@Autowired
	private ConnectedCitiesService connectedCitiesService;
	
	 @RequestMapping(value = "/connected", method= RequestMethod.GET)
	 public String isCitiesConnected(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {		
		 if(connectedCitiesService.isCitiesConnected(origin, destination)) {
			 return "Yes";
		 }
		 else {
			 return "No";
		 }
		
	 }
}
