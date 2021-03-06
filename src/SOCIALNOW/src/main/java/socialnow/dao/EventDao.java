package socialnow.dao;

import org.springframework.stereotype.Repository;
import socialnow.model.Event;
import socialnow.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by mertcan on 22.11.2015.
 */
@Repository
@Transactional
public class EventDao {

    public void create(Event event) {
        entityManager.persist(event);
        return;
    }

    /**\
     * Delete the event from the database.
     *
     * @param event event in database
     */
    public void delete(Event event) {
        if (entityManager.contains(event))
            entityManager.remove(event);
        else
            entityManager.remove(entityManager.merge(event));
        return;
    }

    /**
     *  * Return all the events stored in the database.
     * @return all the events stored in the database
     */
    @SuppressWarnings("unchecked")
    public List<Event> getAll() {
        return entityManager.createQuery("from Event").getResultList();
    }
    public List<Event> getAllForUser(User u) {
        return entityManager.createQuery("from Event where visibleTo = :allVisible or INSTR(visibleTo, :role) > 0 ").setParameter("role",u.getRole()).setParameter("allVisible","all").getResultList();
    }

    public List<Event> getAllByToken(String token){
        return entityManager.createQuery("from Event where event_host_token =:eventtoken").setParameter("eventtoken",token).getResultList();

    }


    /**
     *
     * @param id id of event in database
     * @return the event in the database
     */
    public Event getById(long id) {
        return entityManager.find(Event.class, id);
    }

    public Event getById(String id) {
        return entityManager.find(Event.class,Long.parseLong(id));
    }

    /**
     *  Update the passed event in the database.
     * @param event vent in database
     */
    public void update(Event event) {
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
