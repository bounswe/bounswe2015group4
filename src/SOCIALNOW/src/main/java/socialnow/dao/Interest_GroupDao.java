package socialnow.dao;

import org.springframework.stereotype.Repository;
import socialnow.model.Interest_Group;
import socialnow.model.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Erdem on 12/3/2015.
 */

@Repository
@Transactional
public class Interest_GroupDao {



    @PersistenceContext
    private EntityManager entityManager;
    public void create(Post post) {
        entityManager.persist(post);
        return;
    }

    public List<Interest_Group> getAllByToken(String token){
        return entityManager.createQuery("from Interest_Group where owner_token =:token").setParameter("token",token).getResultList();

    }

    public List<Interest_Group> getAll() {
        return entityManager.createQuery("from Interest_Group").getResultList();
    }

    public Interest_Group getById(long id) {
        return entityManager.find(Interest_Group.class, id);
    }

    public Interest_Group getById(String id) {
        long idlong= Long.parseLong(id);
        return entityManager.find(Interest_Group.class, idlong);
    }





}
