package vttp.ssf.assessment.eventmanagement.models;

public class Event {
    public Integer eventId;
    public String eventName;
    public Integer eventSize;
    public Long eventDate;
    public Integer participants;
    public Event() {
    }
    
    public Event(Integer eventId, String eventName, Integer eventSize, Long eventDate, Integer participants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventSize = eventSize;
        this.eventDate = eventDate;
        this.participants = participants;
    }

    public Integer getEventId() {
        return eventId;
    }
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public Integer getEventSize() {
        return eventSize;
    }
    public void setEventSize(Integer eventSize) {
        this.eventSize = eventSize;
    }
    public Long getEventDate() {
        return eventDate;
    }
    public void setEventDate(Long eventDate) {
        this.eventDate = eventDate;
    }
    public Integer getParticipants() {
        return participants;
    }
    public void setParticipants(Integer participants) {
        this.participants = participants;
    }
    public void addParticipants(Integer newParticipants){
        this.participants+=newParticipants;
    }
    @Override
    public String toString() {
        return getEventId() + "," + getEventName() + ","
                + getEventSize() + "," + getEventDate() + "," + getParticipants();
    }
    
    
}
