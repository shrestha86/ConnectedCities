package com.ss.connected.cities.service;

import com.ss.connected.cities.model.City;

public interface ConnectedCitiesService {
	
	boolean isCitiesConnected(String origin, String destination);
	boolean findconnectedCities(City origin, City destination);

}
