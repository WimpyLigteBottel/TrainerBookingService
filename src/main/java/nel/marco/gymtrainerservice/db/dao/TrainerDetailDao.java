package nel.marco.gymtrainerservice.db.dao;

import nel.marco.gymtrainerservice.db.entity.TrainerDetail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainerDetailDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<TrainerDetail> find(long id) {

        String hql = "SELECT t FROM TrainerDetail t WHERE t.id=:id";

        try {
            TrainerDetail singleResult = entityManager.createQuery(hql, TrainerDetail.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.of(singleResult);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<TrainerDetail> findAll(int maxResults, int index, String email) {

        String hql = "select t from TrainerDetail t where t.email like :emailFilter";

        // crude way but ideally you don't want to search for empty text name it slows down queries
        if (email != null && email.trim().isEmpty()) {
            return findAll(maxResults, index);
        }

        return entityManager.createQuery(hql, TrainerDetail.class)
                .setParameter("emailFilter", "%" + email + "%")// Using the setParameter because of sql injection
                .setMaxResults(maxResults)
                .setFirstResult(index)
                .getResultList();

    }

    private List<TrainerDetail> findAll(int maxResults, int index) {
        String hql = "select t from TrainerDetail t";

        return entityManager.createQuery(hql, TrainerDetail.class)
                .setMaxResults(maxResults)
                .setFirstResult(index)
                .getResultList();
    }

    public void save(TrainerDetail trainerDetail) {
        entityManager.persist(trainerDetail);
    }
}
