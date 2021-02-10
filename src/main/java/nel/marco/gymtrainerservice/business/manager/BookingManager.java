package nel.marco.gymtrainerservice.business.manager;

import nel.marco.gymtrainerservice.business.dto.GymClassDto;
import nel.marco.gymtrainerservice.db.dao.GymClassDao;
import nel.marco.gymtrainerservice.db.dao.GymDao;
import nel.marco.gymtrainerservice.db.dao.TrainerDao;
import nel.marco.gymtrainerservice.db.dao.TrainerDetailDao;
import nel.marco.gymtrainerservice.db.entity.GymClass;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class BookingManager {


    GymDao gymDao;
    TrainerDao trainerDao;
    TrainerDetailDao trainerDetailDao;
    GymClassDao gymClassDao;


    @Transactional
    public void bookAClass(GymClassDto gymClassDto) {

        //TODO: do a check to see if the session can be booked

        //TODO: trainer can't be in another gymclass at the same time

        //TODO: do a check if the trainer is available

        GymClass gymClass = new GymClass();

        gymClass.setBooked(false);
        gymClass.setTimeSlotStart(gymClassDto.getTimeSlotStart());
        gymClass.setTimeSlotEnd(gymClassDto.getTimeSlotEnd());

        gymClass.setTrainerId(gymClassDto.getTrainerId());
        gymClass.setGymId(gymClassDto.getGymId());


        gymClassDao.save(gymClass);


    }


}
