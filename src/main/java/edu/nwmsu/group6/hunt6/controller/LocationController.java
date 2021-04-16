package edu.nwmsu.group6.hunt6.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import edu.nwmsu.group6.hunt6.entity.Location;
import edu.nwmsu.group6.hunt6.repository.LocationRepository;

@RestController
public class LocationController {
//    @Autowired
	@Resource
	private LocationRepository locationRepository;

	ModelAndView mv = new ModelAndView();

	@RequestMapping(value = "/location/random", method = RequestMethod.GET)
	public ModelAndView getRandomLocation() {
		mv.setViewName("index");
		List<Location> locationsList = (List<Location>) locationRepository.findAll();
		List<Long> idList = new ArrayList<Long>();
		for (Location l : locationsList) {
			idList.add(l.getId());
		}
		Random random = new Random();
		int rand = random.nextInt(locationsList.size());
		Location randomLocation = locationsList.get(rand);
		mv.addObject("location",randomLocation);
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

//	@RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
//	public ModelAndView search(@PathVariable("id") Long id, Model model) {
//		Location locationFound = locationRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid location Id:" + id));
//		model.addAttribute("location",locationFound);	
//		mv.setViewName("searchResults");
//		return mv;
//	}

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
	
	@RequestMapping(value = "/location/inorout", method = RequestMethod.GET)
	public boolean isInside(@PathVariable("location") Location loc) {
		double top = 49.3457868D; // # north lat
		double left = -124.7844079D; // # west long
		double right = -66.9513812D; // # east long
		double bottom = 24.7433195D; // # south lat
		return bottom <= loc.getLatitude() && loc.getLatitude() <= top && left <= loc.getLongitude() && loc.getLongitude() <= right;		
	}

}