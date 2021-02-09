package nel.marco.gymtrainerservice.business.manager;

import nel.marco.gymtrainerservice.business.dto.TrainerDto;
import nel.marco.gymtrainerservice.db.dao.TrainerDao;
import nel.marco.gymtrainerservice.db.dao.TrainerDetailDao;
import nel.marco.gymtrainerservice.db.entity.Trainer;
import nel.marco.gymtrainerservice.db.entity.TrainerDetail;
import nel.marco.gymtrainerservice.mapper.TrainerDetailMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class TrainerDetailManager {

    private final TrainerManager trainerManager;
    private final TrainerDetailDao trainerDetailDao;
    private final TrainerDao trainerDao;

    public TrainerDetailManager(TrainerManager trainerManager, TrainerDetailDao trainerDetailDao, TrainerDao trainerDao) {
        this.trainerManager = trainerManager;
        this.trainerDetailDao = trainerDetailDao;
        this.trainerDao = trainerDao;
    }

    public Optional<TrainerDetail> find(long trainerId) {

        return trainerDetailDao.find(trainerId);
    }


    @Transactional // in case it fails to insert into database it can rollback
    public boolean create(TrainerDto trainerDto) {

        Optional<Trainer> trainer = trainerDao.find(trainerDto.getId());

        if (!trainer.isPresent()) {
            // should log or say why it could not have been created
            return false;
        }


        TrainerDetail trainerDetail = TrainerDetailMapper.INSTANCE.mapToV1(trainerDto);

        trainerDetailDao.save(trainerDetail);

        return true;
    }


}
