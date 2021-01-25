package nel.marco.gymtrainerservice.business.manager;

import nel.marco.gymtrainerservice.business.dto.GymDto;
import nel.marco.gymtrainerservice.db.dao.GymCrudRepository;
import nel.marco.gymtrainerservice.db.dao.GymDao;
import nel.marco.gymtrainerservice.db.entity.Gym;
import nel.marco.gymtrainerservice.mapper.GymMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GymManager {

    private final GymCrudRepository gymCrudRepository;
    private final GymDao gymDao;

    public GymManager(GymCrudRepository gymCrudRepository, GymDao gymDao) {
        this.gymCrudRepository = gymCrudRepository;
        this.gymDao = gymDao;
    }


    public List<GymDto> findAll(int maxResults, int index, String filterName) {

        return gymDao.findAll(maxResults, index, filterName)
                .stream()
                .map(GymMapper.INSTANCE::mapToV1)
                .collect(Collectors.toList());
    }

    public Optional<GymDto> find(long id) {

        Optional<Gym> optionalGym = gymDao.find(id);

        return optionalGym.map(GymMapper.INSTANCE::mapToV1);
    }


    @Transactional
    public void create(GymDto gymDto) {

        Gym gym = new Gym();
        gym.setName(gymDto.getName());

        gymCrudRepository.save(gym);
    }


}
