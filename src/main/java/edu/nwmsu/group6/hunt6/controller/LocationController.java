package edu.nwmsu.group6.hunt6.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

import edu.nwmsu.group6.hunt6.model.Location;
import edu.nwmsu.group6.hunt6.repository.LocationRepository;

@RestController
public class LocationController {

	@Resource
    private LocationRepository loc;
	
	public void createLocation(Location l) {
		loc.save(l);
	}
	
	public Location getLocation(int id) {
		Location lg=loc.findById(id).get();
		return lg;
	}
	
	public void updateLocation(int id,double lat, double lon) {
		Location loca = loc.findById(id).get();
		loca.setLatitude(lat);
		loca.setLongitude(lon);
		loc.save(loca);
	}
	
	public void deleteLocation(int id) {
		loc.deleteById(id);
	}
	
}