package nel.marco.gymtrainerservice.db.dao;

import nel.marco.gymtrainerservice.db.entity.GymClass;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
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

    public boolean isTrainerAvailable(long trainerId, Date timeSlotStart, Date timeSlotEnd) {

        String hql = "SELECT g FROM GymClass g " +
                "WHERE g.trainerId=:id " +
                "AND (g.timeSlotStart is between :start and :end OR g.timeSlotEnd is between :start and :end)";


        try {
            List<GymClass> singleResult = entityManager.createQuery(hql, GymClass.class)
                    .setParameter("id", trainerId)
                    .setParameter("start", timeSlotStart)
                    .setParameter("end", timeSlotEnd)
                    .getResultList();

            return singleResult.size() == 0;
        } catch (Exception e) {
            return false;
        }


    }
}
