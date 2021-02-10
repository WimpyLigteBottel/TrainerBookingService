package nel.marco.gymtrainerservice.db.dao;

import nel.marco.gymtrainerservice.db.entity.GymClass;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class GymClassDao {

    @PersistenceContext
    private EntityManager entityManager;


    public List<GymClass> findAll(int maxResults, int index) {

        String hql = "select g from GymClass g";

        return entityManager.createQuery(hql, GymClass.class)
                .setMaxResults(maxResults)
                .setFirstResult(index)
                .getResultList();

    }

    public Optional<GymClass> find(long id) {


        String hql = "SELECT g FROM GymClass g WHERE g.id=:id";


        try {
            GymClass singleResult = entityManager.createQuery(hql, GymClass.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.of(singleResult);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    public void save(GymClass gymClass) {
        entityManager.persist(gymClass);
    }
}
