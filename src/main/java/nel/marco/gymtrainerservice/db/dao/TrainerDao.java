package nel.marco.gymtrainerservice.db.dao;

import nel.marco.gymtrainerservice.db.entity.Trainer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainerDao {

  @PersistenceContext private EntityManager entityManager;

  public Optional<Trainer> find(long id) {

    String hql = "SELECT t FROM Trainer t WHERE t.id=:id";

    try {
      Trainer singleResult =
          entityManager.createQuery(hql, Trainer.class).setParameter("id", id).getSingleResult();

      return Optional.of(singleResult);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  public List<Trainer> findAll(int maxResults, int index, String filterName) {

    String hql = "select t from Trainer t where t.name like :filterName";

    // crude way but ideally you don't want to search for empty text name it slows down queries
    if (filterName != null && filterName.trim().isEmpty()) {
      return findAll(maxResults, index);
    }

    return entityManager
        .createQuery(hql, Trainer.class)
        .setParameter(
            "filterName", "%" + filterName + "%") // Using the setParameter because of sql injection
        .setMaxResults(maxResults)
        .setFirstResult(index)
        .getResultList();
  }

  private List<Trainer> findAll(int maxResults, int index) {
    String hql = "select t from Trainer t";

    return entityManager
        .createQuery(hql, Trainer.class)
        .setMaxResults(maxResults)
        .setFirstResult(index)
        .getResultList();
  }

  public void save(Trainer trainer) {
    entityManager.persist(trainer);
  }
}
