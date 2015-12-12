package socialnow.dao;

import org.springframework.stereotype.Repository;
import socialnow.model.Instant_Event;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Erdem on 12/12/2015.
 */
@Repository
@Transactional
public class Instant_EventDao {


    public void create(Instant_Event event) {
        entityManager.persist(event);
        return;
    }

    /**
     * Delete the event from the database.
     */
    public void delete(Instant_Event event) {
        if (entityManager.contains(event))
            entityManager.remove(event);
        else
            entityManager.remove(entityManager.merge(event));
        return;
    }

    /**
     * Return all the events stored in the database.
     */
    @SuppressWarnings("unchecked")
    public List<Instant_Event> getAll() {
        return entityManager.createQuery("from Event").getResultList();
    }

    public List<Instant_Event> getAllByToken(String token){
        return entityManager.createQuery("from Event where event_host_token =:eventtoken").setParameter("eventtoken",token).getResultList();

    }


    /**
     * Return the event having the passed id.
     */
    public Instant_Event getById(long id) {
        return entityManager.find(Instant_Event.class, id);
    }

    public Instant_Event getById(String id) {
        return entityManager.find(Instant_Event.class,Long.parseLong(id));
    }
    /**
     * Update the passed event in the database.
     */
    public void update(Instant_Event event) {
        entityManager.merge(event);
        return;
    }

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    // An EntityManager will be automatically injected from entityManagerFactory
    // setup on DatabaseConfig class.
    @PersistenceContext
    private EntityManager entityManager;





}
