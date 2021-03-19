package edu.nwmsu.group6.hunt6.controller;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmsu.group6.hunt6.entity.Location;
import edu.nwmsu.group6.hunt6.repository.LocationRepository;

@RestController
public class LocationController {

	@Resource
	private LocationRepository locationRepository;

	ModelAndView mv = new ModelAndView();

	@RequestMapping(value = "/addlocation")
	public ModelAndView addLocation() {
		mv.setViewName("addLocation.html");
		return mv;
	}

	@RequestMapping(value = "/location", method = RequestMethod.POST)
	public ModelAndView addNewLocation(Location location) {
		locationRepository.save(location);
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public ModelAndView viewLocations() {
		mv.setViewName("viewLocations.html");
		Iterable<Location> locationsList = locationRepository.findAll();
		mv.addObject("locations", locationsList);
		return mv;
	}

	@RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
	public ModelAndView search(@PathVariable("id") Long id) {
		Location locationFound = locationRepository.findById(id).orElse(new Location());
		mv.addObject(locationFound);
		mv.setViewName("searchResults");
		return mv;
	}

	@RequestMapping(value = "/location/{id}", method = RequestMethod.PATCH)
	public ModelAndView editLocation(Location location) {
		locationRepository.save(location);
		mv.setViewName("editLocation");
		return mv;
	}

	@RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteLocation(Location location) {
		Optional<Location> locationFound = locationRepository.findById(location.getId());
		if (locationFound.isPresent()) {
			locationRepository.delete(location);
		}
		return viewLocations();
	}

}