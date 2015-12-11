package socialnow.dao;
import org.springframework.stereotype.Repository;
import socialnow.model.Event;
import socialnow.model.Notification;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaDelete;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Erdem on 12/11/2015.
 */


@Repository
@Transactional
public class NotificationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Notification notf) {
        entityManager.persist(notf);
        return;
    }




    public void delete(Notification notf) {
        if (entityManager.contains(notf))
            entityManager.remove(notf);
        else
            entityManager.remove(entityManager.merge(notf));
        return;
    }

    public Notification getById(long id) {
        return entityManager.find(Notification.class, id);
    }

    public Notification getById(String id) {
        return entityManager.find(Notification.class,Long.parseLong(id));
    }


    public List<Notification> getAllByToken(String token){
        return entityManager.createQuery("from Notification where to_user =:to_user").setParameter("to_user",token).getResultList();

    }

    public void removeToUser(String token){
        String hql = "delete from Notification  where to_user =:token";
        entityManager.createQuery(hql).setParameter("token", token).executeUpdate();

    }




}
