package nel.marco.gymtrainerservice.business.manager;

import nel.marco.gymtrainerservice.business.dto.TrainerDto;
import nel.marco.gymtrainerservice.db.dao.GymDao;
import nel.marco.gymtrainerservice.db.dao.TrainerDao;
import nel.marco.gymtrainerservice.db.entity.Gym;
import nel.marco.gymtrainerservice.db.entity.Trainer;
import nel.marco.gymtrainerservice.mapper.TrainerMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TrainerManager {

  private final TrainerDao trainerDao;
  private final GymDao gymDao;

  public TrainerManager(TrainerDao trainerDao, GymDao gymDao) {
    this.trainerDao = trainerDao;
    this.gymDao = gymDao;
  }

  public List<TrainerDto> findAll(int maxResults, int index, String filterName) {

    return trainerDao.findAll(maxResults, index, filterName).stream()
        .map(TrainerMapper.INSTANCE::mapToV1)
        .collect(Collectors.toList());
  }

  public Optional<TrainerDto> find(long id) {

    Optional<Trainer> optionalTrainer = trainerDao.find(id);

    return optionalTrainer.map(TrainerMapper.INSTANCE::mapToV1);
  }

  @Transactional // incase it fails to insert into database it can rollback
  public boolean create(TrainerDto trainerDto) {

    Optional<Gym> gym = gymDao.find(trainerDto.getGymDto().getId());

    if (!gym.isPresent()) {
      // I prefer to return enum with what the state is... but it all depends
      return false;
    }

    Trainer trainer = new Trainer();
    trainer.setName(trainerDto.getName());
    trainer.setGym(gym.get());

    trainerDao.save(trainer);

    return true;
  }
}
