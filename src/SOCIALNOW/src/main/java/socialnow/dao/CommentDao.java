package socialnow.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import socialnow.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Erdem on 12/13/2015.
 *
 *
 */

@Repository
@Transactional
public class CommentDao {


    @PersistenceContext
    private EntityManager entityManager;
    public void create(Comment comment) {
        entityManager.persist(comment);
        return;
    }

    public List<Comment> getAllByToken(String token){
        return entityManager.createQuery("from Post where owner_token =:token").setParameter("token",token).getResultList();

    }

    public List<Comment> getAll() {
        return entityManager.createQuery("from Comment").getResultList();
    }

    public Comment getById(long id) {
        return entityManager.find(Comment.class, id);
    }

    public Comment getById(String id) {
        return entityManager.find(Comment.class,Long.parseLong(id));
    }
}
