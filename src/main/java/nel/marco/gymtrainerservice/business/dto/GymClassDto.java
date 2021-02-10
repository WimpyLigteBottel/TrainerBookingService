package nel.marco.gymtrainerservice.business.dto;

import java.util.Date;

public class GymClassDto {


    private long id;
    private long trainerId;
    private long gymId;

    private Date timeSlotStart;
    private Date timeSlotEnd;
    private boolean isBooked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }

    public long getGymId() {
        return gymId;
    }

    public void setGymId(long gymId) {
        this.gymId = gymId;
    }

    public Date getTimeSlotStart() {
        return timeSlotStart;
    }

    public void setTimeSlotStart(Date timeSlotStart) {
        this.timeSlotStart = timeSlotStart;
    }

    public Date getTimeSlotEnd() {
        return timeSlotEnd;
    }

    public void setTimeSlotEnd(Date timeSlotEnd) {
        this.timeSlotEnd = timeSlotEnd;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
