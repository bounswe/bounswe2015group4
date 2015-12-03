package socialnow.dao;

import org.springframework.stereotype.Repository;
import socialnow.model.Event;
import socialnow.model.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Erdem on 11/23/2015.
 */


@Repository
@Transactional
public class PostDao {
    // setup on DatabaseConfig class.
    @PersistenceContext
    private EntityManager entityManager;
    public void create(Post post) {
        entityManager.persist(post);
        return;
    }

    public List<Post> getAllByToken(String token){
        return entityManager.createQuery("from Post where owner_token =:token").setParameter("token",token).getResultList();

    }

    public List<Post> getAll() {
        return entityManager.createQuery("from Post").getResultList();
    }

    public Post getById(long id) {
        return entityManager.find(Post.class, id);
    }

    public Post getById(String id) {
        return entityManager.find(Post.class,Long.parseLong(id));
    }
}
