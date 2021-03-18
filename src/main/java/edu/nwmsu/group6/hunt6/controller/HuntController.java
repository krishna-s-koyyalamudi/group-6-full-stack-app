package edu.nwmsu.group6.hunt6.controller;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmsu.group6.hunt6.entity.Location;
import edu.nwmsu.group6.hunt6.repository.LocationRepository;

@Controller
//@RequestMapping("/hunt")
public class HuntController {

	@Resource
	private LocationRepository locationRepository;

	@RequestMapping("/")
	@ResponseBody
	public ModelAndView show() {
		System.out.println("coming to index");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping("addLocation")
	public ModelAndView addLocation() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addLocation.html");
//      Iterable<Trainer> LocationsList = LocationRepository.findAll();
//      mv.addObject("Location",trainersList);
		return mv;

	}

	@RequestMapping("ViewLocations")
	public ModelAndView ViewLists() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ViewLocations.html");
		Iterable<Location> locationsList = locationRepository.findAll();
		mv.addObject("locations", locationsList);
		return mv;
	}
	
	@RequestMapping("editLocation")
    public ModelAndView editLocation(Location location) {
		ModelAndView mv = new ModelAndView();
		locationRepository.save(location);
        mv.setViewName("editLocation");
        return mv;
    }
	
	@RequestMapping("deleteLocation")
    public ModelAndView deleteDog(Location location) {
        Optional<Location> locationFound = locationRepository.findById(location.getId());
        if (locationFound.isPresent()) {
            locationRepository.delete(location);
        }
        return show();
    }

	@GetMapping
	public String huntDemo() {
		return "serviceUp";
	}
}
