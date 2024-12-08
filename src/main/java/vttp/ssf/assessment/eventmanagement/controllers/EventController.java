package vttp.ssf.assessment.eventmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;





@Controller
@RequestMapping("/events")
public class EventController {
	@Autowired
	DatabaseService databaseService;
		
	@GetMapping("/listing")
	public ModelAndView displayEvents() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("allEvents");
		List<Event> eventsList = databaseService.getAllEvents();
		mav.addObject("eventsList", eventsList);
		return mav;
	}	
	
	

}
