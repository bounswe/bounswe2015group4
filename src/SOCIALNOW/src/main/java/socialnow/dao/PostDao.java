package socialnow.dao;

import org.springframework.stereotype.Repository;
import socialnow.model.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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



}
