package com.ss.connected.cities.model;

import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


public class City {
	
	private String name;
	 private Set<City> cityNearby = new HashSet<>();
	 
	 public String getName() {
			return name;
		}
	public void setName(String name) {
			this.name = name;
		}
	
	 private City(String name) {
	        Objects.requireNonNull(name);
	        this.name = name.trim().toUpperCase();
	    }
	 private City() {
	    }

	public static City connect(String name) {
        return new City(name);
    }
	
	 public City addCityNearBy(City city) {
		 cityNearby.add(city);
	        return this;
	    }
	 
	 public Set<City> getNearbyCity() {
	        return cityNearby;
	    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof City)) return false;
        City city = (City) obj;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

	
	
}
