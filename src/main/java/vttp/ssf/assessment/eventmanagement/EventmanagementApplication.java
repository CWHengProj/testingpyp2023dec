package vttp.ssf.assessment.eventmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@SpringBootApplication
public class EventmanagementApplication implements CommandLineRunner{
	@Autowired
	DatabaseService databaseService;
	
	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//command line runner executes readFile
		String fileName="events.json";
		if (args.length>1){
			fileName = args[0]; //filepath is at root, thus just call fileName
		}
		List<Event> eventsList = databaseService.readFile(fileName);
		//display the list of windows here
		for(Event event: eventsList){
			System.out.println(event.toString());
			databaseService.saveRecord(event);
		}
	}
	

}
