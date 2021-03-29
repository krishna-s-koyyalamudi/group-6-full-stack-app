package edu.nwmsu.group6.hunt6.controller;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import antlr.collections.List;
import edu.nwmsu.group6.hunt6.entity.Location;
import edu.nwmsu.group6.hunt6.repository.LocationRepository;

@RestController
public class LocationController {
    @Autowired
	@Resource
	private LocationRepository locationRepository;

	ModelAndView mv = new ModelAndView();
	
	@RequestMapping(value = "/randomLocation", method = RequestMethod.GET)
	public ModelAndView getRandomLocation() {
		mv.setViewName("index");
		Iterable<Location> locationsList = locationRepository.findAll();
	    Random rand = new Random();
		mv.addObject("locations", locationsList);
		return mv;
	}


	@RequestMapping(value = "/addlocation")
	public ModelAndView addLocation() {
		mv.setViewName("addLocation.html");
		return mv;
	}

	@RequestMapping(value = "/location/create", method = RequestMethod.POST)
	public ModelAndView addNewLocation(Location location) {
		locationRepository.save(location);
		ModelAndView mav = new ModelAndView("redirect:/locations");
		return mav;
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

	@RequestMapping(value = "/location/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editLocation(@PathVariable("id") Long id, Model model) {
		Location location = locationRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid location Id:" + id));
		model.addAttribute("location", location);
		mv.setViewName("editLocation");
		return mv;
	}

	@RequestMapping(value = "/location/update/{id}", method = RequestMethod.POST)
	public ModelAndView updateLocation(@PathVariable("id") long id, @Valid Location location, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			location.setId(id);
			ModelAndView mav = new ModelAndView("redirect:/locations");
			return mav;
		}
		locationRepository.save(location);
		ModelAndView mav = new ModelAndView("redirect:/locations");
		return mav;
	}

	@RequestMapping(value = "/location/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteLocation(@PathVariable("id") Long id, Model model) {
		Location location = locationRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid location Id:" + id));
		locationRepository.delete(location);
		ModelAndView mav = new ModelAndView("redirect:/locations");
		return mav;
	}

}