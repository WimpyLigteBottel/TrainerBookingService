package nel.marco.gymtrainerservice.business.manager;

import nel.marco.gymtrainerservice.business.dto.GymClassDto;
import nel.marco.gymtrainerservice.db.dao.GymClassDao;
import nel.marco.gymtrainerservice.db.dao.GymDao;
import nel.marco.gymtrainerservice.db.dao.TrainerDao;
import nel.marco.gymtrainerservice.db.dao.TrainerDetailDao;
import nel.marco.gymtrainerservice.db.entity.GymClass;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookingManager {

  GymDao gymDao;
  TrainerDao trainerDao;
  TrainerDetailDao trainerDetailDao;
  GymClassDao gymClassDao;

  @Transactional
  public void bookAClass(GymClassDto dto) {

    boolean isTrainerAvailable =
        gymClassDao.isTrainerAvailable(
            dto.getTrainerId(), dto.getTimeSlotStart(), dto.getTimeSlotEnd());
    if (!isTrainerAvailable) {
      // TODO: add response to say that it failed to book... Maybe implement error logic in here
      // somewhere
      System.out.println("ERROR!");
    }
    ;

    // TODO: do a check to see if the session can be booked

    // TODO: trainer can't be in another gymclass at the same time

    // TODO: do a check if the trainer is available

    GymClass gymClass = new GymClass();

    gymClass.setBooked(false);
    gymClass.setTimeSlotStart(dto.getTimeSlotStart());
    gymClass.setTimeSlotEnd(dto.getTimeSlotEnd());

    gymClass.setTrainerId(dto.getTrainerId());
    gymClass.setGymId(dto.getGymId());

    gymClassDao.save(gymClass);
  }
}
