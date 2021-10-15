package nel.marco.gymtrainerservice.business.manager;

import nel.marco.gymtrainerservice.db.dao.GymClassDao;
import nel.marco.gymtrainerservice.db.dao.GymDao;
import nel.marco.gymtrainerservice.db.dao.TrainerDao;
import nel.marco.gymtrainerservice.db.dao.TrainerDetailDao;
import nel.marco.gymtrainerservice.db.entity.GymClass;
import nel.marco.gymtrainerservice.db.entity.Trainer;
import nel.marco.gymtrainerservice.rest.v1.model.BookingRequest;
import nel.marco.gymtrainerservice.rest.v1.type.TimeSlotRange;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

@Component
public class BookingManager {

  private final GymDao gymDao;
  private final TrainerDao trainerDao;
  private final TrainerDetailDao trainerDetailDao;
  private final GymClassDao gymClassDao;

  public BookingManager(GymDao gymDao, TrainerDao trainerDao, TrainerDetailDao trainerDetailDao, GymClassDao gymClassDao) {
    this.gymDao = gymDao;
    this.trainerDao = trainerDao;
    this.trainerDetailDao = trainerDetailDao;
    this.gymClassDao = gymClassDao;
  }

  @Transactional
  public boolean bookAClass(BookingRequest bookingRequest) {


    Optional<Trainer> trainer = trainerDao.find(bookingRequest.getTrainerId());
    if(trainer.isEmpty()){
      return false;
    }


    Date endingTime = getEndingTime(bookingRequest);
    boolean isTrainerAvailable =
        gymClassDao.isTrainerAvailable(
            bookingRequest.getTrainerId(), bookingRequest.getStartTime(), endingTime);
    if (!isTrainerAvailable) {
      // TODO: add response to say that it failed to book... Maybe implement error logic in here
      // somewhere
      System.out.println("Failed to book class, trainer is not available!");
      return false;
    }

    GymClass gymClass = new GymClass();

    gymClass.setBooked(false);
    gymClass.setTimeSlotStart(bookingRequest.getStartTime());
    gymClass.setTimeSlotEnd(endingTime);

    gymClass.setTrainerId(bookingRequest.getTrainerId());
    gymClass.setGymId(bookingRequest.getGymId());

    gymClassDao.save(gymClass);

    return true;
  }

  private Date getEndingTime(BookingRequest bookingRequest) {

    Date startTime = bookingRequest.getStartTime();
    TimeSlotRange timeSlotRange = bookingRequest.getTimeSlotRange();

    LocalDateTime startLocalDateTime = LocalDateTime.from(startTime.toInstant()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    switch (timeSlotRange){
      case _10MIN -> startLocalDateTime = startLocalDateTime.plusMinutes(10);
      case _20MIN -> startLocalDateTime = startLocalDateTime.plusMinutes(20);
      case _30MIN -> startLocalDateTime = startLocalDateTime.plusMinutes(30);
      case _40MIN -> startLocalDateTime = startLocalDateTime.plusMinutes(40);
      case _50MIN -> startLocalDateTime = startLocalDateTime.plusMinutes(50);
      case _60MIN -> startLocalDateTime = startLocalDateTime.plusMinutes(60);
    }

    return new Date(startLocalDateTime.toEpochSecond(ZoneOffset.of(ZoneId.systemDefault().getId())));
  }

  public void acceptBooking(long gymClassId, long trainerId) {
    Optional<GymClass> gymClass = gymClassDao.find(gymClassId,trainerId);


    gymClass.ifPresent(gymClass1 -> {
        gymClass1.setBooked(true);
    });
  }
}
