package nel.marco.gymtrainerservice.db.dao;

import nel.marco.gymtrainerservice.db.entity.Gym;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/*

The reason why i have dao + crud is to show that you can use both and depending on what you want to achieve
you can use the one that works best for you

 */

@Repository
public class GymDao {

  @PersistenceContext private EntityManager entityManager;

  public List<Gym> findAll(int maxResults, int index, String filterName) {

    String hql = "select g from Gym g where g.name like :filterName";

    return entityManager
        .createQuery(hql, Gym.class)
        .setParameter(
            "filterName", "%" + filterName + "%") // Using the setParameter because of sql injection
        .setMaxResults(maxResults)
        .setFirstResult(index)
        .getResultList();
  }

  public Optional<Gym> find(long id) {

    String hql = "SELECT g FROM Gym g WHERE g.id=:id";

    try {
      Gym singleResult =
          entityManager.createQuery(hql, Gym.class).setParameter("id", id).getSingleResult();

      return Optional.of(singleResult);
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
