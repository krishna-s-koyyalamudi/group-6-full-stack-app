package edu.nwmsu.group6.hunt6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/hunt")
public class HuntController {
	
	
	
	@RequestMapping("/hunt")
	@ResponseBody
	public ModelAndView show()
	{
		System.out.println("coming to index");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("addQuest")
	  public ModelAndView addLocation(){
		  ModelAndView mv=new ModelAndView();
      mv.setViewName("addLocation.html");
//      Iterable<Trainer> LocationsList = LocationRepository.findAll();
//      mv.addObject("Location",trainersList);
      
  return mv;
  
  }
	
	
	@GetMapping
	public String huntDemo() {
		return "serviceUp";
	}
}
