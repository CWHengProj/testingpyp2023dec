package vttp.ssf.assessment.eventmanagement.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;
@Service
public class DatabaseService {
    @Autowired
    RedisRepository redisRepository;
    public List<Event> readFile(String fileName) throws IOException{
        //testing some code changes here to make the file work on docker and railway
        ClassPathResource cpr = new ClassPathResource(fileName);
        InputStream is = cpr.getInputStream();
        
        // //fileName will be events.json
        // File file = new File(fileName);
        // FileReader fr = new FileReader(file);
        JsonReader jReader = Json.createReader(is);
        JsonArray jArray =jReader.readArray();  
        List<Event> eventsList = new ArrayList<>(); 
        for (JsonValue jValue: jArray){
            //manipulate the values here
            JsonObject jObject = (JsonObject) jValue;
            Integer id = jObject.getInt("eventId");
            String name = jObject.getString("eventName");
            Integer size = jObject.getInt("eventSize");
            Long date = jObject.getJsonNumber("eventDate").longValue();
            Integer participants = jObject.getInt("participants");
            
            Event event = new Event(id,name,size,date,participants);
            eventsList.add(event);
            
        }    
        return eventsList;
    }
    public void saveRecord(Event event) {
        redisRepository.saveRecord(event);
    }
    public Integer getNumberOfEvents(){
        return redisRepository.getNumberOfEvents();
    }
    public Event getEvent(String index){
        return redisRepository.getEvent(index);
    }
    public List<Event> getAllEvents() {
        return redisRepository.getAllEvents();        
    }
}
