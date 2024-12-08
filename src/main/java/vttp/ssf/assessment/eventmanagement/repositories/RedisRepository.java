package vttp.ssf.assessment.eventmanagement.repositories;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.ssf.assessment.eventmanagement.constants.Constant;
import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {
	@Autowired
    @Qualifier(Constant.template01)
    RedisTemplate<String, Object> template;	
	String redisKey = Constant.redisKey;
	//saveRecord function save list of events objects to Redis
	public void saveRecord(Event event) {
		//break down the event to be identifiable via the id
		String id = event.getEventId().toString();
		//push in the values
		template.opsForHash().put(redisKey,id,event);
	}
	public Integer getNumberOfEvents() {
		Long eventSize = template.opsForHash().size(redisKey);		
		return (int) (long) eventSize;
	}
	//NOTE - stringRedisSerializer requires string input, so any function using it will accept String inputs, not int inputs
	public Event getEvent(String index) {
		return (Event) template.opsForHash().get(redisKey, index);
	}
	public List<Event> getAllEvents() {
		//Best practice
		// List<Event> eventList = template.opsForHash().values(redisKey).stream().map(e -> (Event) e).collect(Collectors.toList());
		// return eventList;
		
		List<Object> allObjects = template.opsForHash().values(redisKey);
		List<Event> eventList = new ArrayList<>();
		for (Object obj: allObjects){
			eventList.add((Event) obj);
		}
		return eventList;
	}

	

}
