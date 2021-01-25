package nel.marco.gymtrainerservice.db.dao;

import nel.marco.gymtrainerservice.db.entity.Gym;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymCrudRepository extends CrudRepository<Gym, Long> {
}
