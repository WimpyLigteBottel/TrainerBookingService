package nel.marco.gymtrainerservice.db.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/*
    Ideally I would have liked to just call it class but adding Gym in front to make things
    understandable and not class with naming conventions
*/
@Entity
public class GymClass {

  @Id
  // Generally I don't like auto generation because it makes batch inserting difficult. Could always
  // change this
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(unique = false)
  private long trainerId;

  @Column(unique = false)
  private long gymId;

  @Column(unique = false)
  private Date timeSlotStart;

  @Column(unique = false)
  private Date timeSlotEnd;

  @Column(unique = false)
  private boolean isBooked;

  @CreationTimestamp private Date inserted;

  @UpdateTimestamp private Date updated;

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

  public Date getInserted() {
    return inserted;
  }

  public void setInserted(Date inserted) {
    this.inserted = inserted;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }
}
