package edu.nwmsu.group6.hunt6.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.nwmsu.group6.hunt6.entity.Location;
import edu.nwmsu.group6.hunt6.exception.ResourceNotFoundException;
import edu.nwmsu.group6.hunt6.repository.LocationRepository;

@RestController
public class LocationController {

	@Resource
	private LocationRepository locationRepository;

	@GetMapping("/locations")
	public Page<Location> getLocations(Pageable pageable) {
		return locationRepository.findAll(pageable);
	}
	
	@GetMapping("/location/{locationId}")
	public Page<Location> getLocation(@PathVariable int locationId, @Validated @RequestBody Location locationRequest) {
		Location location=locationRepository.findById(locationId).get();
		return (Page<Location>) location;
	}

	@PostMapping("/locations")
	public void createLocation(Location l) {
		locationRepository.save(l);
	}

	@PutMapping("/locations/{locationId}")
	public Location updateLocation(@PathVariable int locationId, @Validated @RequestBody Location locationRequest) {
		return locationRepository.findById(locationId).map(location -> {
			location.setLocation_name(locationRequest.getLocation_name());
			location.setLatitude(locationRequest.getLatitude());
			location.setLongitude(locationRequest.getLongitude());
			return locationRepository.save(location);
		}).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));
	}

	@DeleteMapping("/locations/{locationId}")
	public ResponseEntity<?> deleteLocation(@PathVariable int locationId) {
		return locationRepository.findById(locationId).map(location -> {
			locationRepository.delete(location);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));
	}
}