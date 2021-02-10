package nel.marco.gymtrainerservice.rest.v1.model;

import nel.marco.gymtrainerservice.rest.v1.type.TimeSlotRange;

import java.util.Date;

public class BookingRequest {


    private long id;
    private long gymId;
    private String name;
    private Date startTime;
    private TimeSlotRange timeSlotRange;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGymId() {
        return gymId;
    }

    public void setGymId(long gymId) {
        this.gymId = gymId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public TimeSlotRange getTimeSlotRange() {
        return timeSlotRange;
    }

    public void setTimeSlotRange(TimeSlotRange timeSlotRange) {
        this.timeSlotRange = timeSlotRange;
    }
}
