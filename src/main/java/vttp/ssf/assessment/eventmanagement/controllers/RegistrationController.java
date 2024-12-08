package vttp.ssf.assessment.eventmanagement.controllers;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.User;

import vttp.ssf.assessment.eventmanagement.services.DatabaseService;


@Controller
@RequestMapping("events")
public class RegistrationController {
    @Autowired
    DatabaseService databaseService;

    @GetMapping("/registerForm/{id}")
	public String register(@PathVariable("id") String id, Model model) {
        model.addAttribute("id",id);
        Event event =databaseService.getEvent(id);
        String eventName = event.getEventName();
        model.addAttribute("eventName", eventName);
        model.addAttribute("currentDate", LocalDate.now());
		User user = new User();
		model.addAttribute("user",user);
		return "eventRegister";
	}
	@PostMapping("/registerForm/{id}")
	public String processRegistration(@PathVariable("id") String id, @Valid @ModelAttribute("user") User user, BindingResult result, Model model) {	
        boolean validationError=false;
		if(result.hasErrors()){
			return "eventRegister";
		}
        int userAge = Period.between(user.getDob(),LocalDate.now()).getYears();
        if(userAge<21){
            model.addAttribute("ageError","User must be older than 21 years old to register.");
            validationError= true;
        }
        if((databaseService.getEvent(id).getParticipants()+user.getNumTickets())>databaseService.getEvent(id).getEventSize()){
            model.addAttribute("ticketError","You have requested more than the remaining amount of tickets.");
            validationError=true;
        }
        if(validationError){
            return "errorPage";
        }
        databaseService.getEvent(id).addParticipants(user.getNumTickets());//update the number of participants once the check is successful
		return "redirect:/events/success/{id} "; //redirects it to the REQUEST, not the file
	}
    @GetMapping("/success/{id}")
    public String registrationSuccess(@PathVariable("id") String id,Model model){
        Event event =databaseService.getEvent(id);
        String eventName = event.getEventName();
        model.addAttribute("eventName", eventName);
        model.addAttribute("currentDate", LocalDate.now());
        return "successPage";
    }
    

}
