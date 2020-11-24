package com.ss.connected.cities.service;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.springframework.util.StringUtils;

import com.ss.connected.cities.model.City;


@Component
public class ConnectedCitiesServiceImpl implements ConnectedCitiesService{
	
    private Map<String, City> cityPath = new HashMap<>();

	@Value("${data.file:classpath:city.txt}")
	    private String Cities;
	 
	 @Autowired
	    private ResourceLoader resourceLoader;

	
	 public Map<String, City> getCityPath() {
	        return cityPath;
	    }
	 
	 
	 public City getCity(String name) {
	        return cityPath.get(name);
	    }

   
	@PostConstruct
	public void readFile() throws IOException{
		 Resource resource = resourceLoader.getResource(Cities);

	        InputStream is;
	        if (!resource.exists()) {
	            is = new FileInputStream(new File(Cities));
	        } else {
	            is = resource.getInputStream();
	        }

	        Scanner scanner = new Scanner(is);

	        while (scanner.hasNext()) {

	            String line = scanner.nextLine();
	            if (StringUtils.isEmpty(line)) continue;	            
	            String[] split = line.split(",");
	            String FirstKey = split[0].trim().toUpperCase();
	            String LastKey = split[1].trim().toUpperCase();

	            if (!FirstKey.equals(LastKey)) {
	                City A = cityPath.getOrDefault(FirstKey, City.connect(FirstKey));
	                City B = cityPath.getOrDefault(LastKey, City.connect(LastKey));

	                A.addCityNearBy(B);
	                B.addCityNearBy(A);

	                cityPath.put(A.getName(), A);
	                cityPath.put(B.getName(), B);
	            }
	        }
		
	}

	@Override
	public boolean findconnectedCities(City origin, City destination) {
		
		 if (origin.equals(destination)) return true;

	     if (origin.getNearbyCity().contains(destination)) return true;

	        Set<City> path = new HashSet<>(Collections.singleton(origin));

	        Deque<City> nearByCityList = new ArrayDeque<>(origin.getNearbyCity());


	        while (!nearByCityList.isEmpty()) {
	        	
	            City city = nearByCityList.getLast();

	            if (city.equals(destination)) return true;


	            if (!path.contains(city)) {

	            	path.add(city);
	                nearByCityList.addAll(city.getNearbyCity());
	                nearByCityList.removeAll(path);

	            } else {
	            	nearByCityList.removeAll(Collections.singleton(city));
	            }
	        }
	        return false;
	}
	public boolean isCitiesConnected(String origin, String destination) {
		  City originCity = getCity(origin.toUpperCase());
	      City destCity = getCity(destination.toUpperCase());
	      
	      if(originCity!=null && destCity!=null) {
	    	  return findconnectedCities(originCity, destCity);
	      }
	      else{
	    	  return false;
	      }	    

	}
	
	

}
