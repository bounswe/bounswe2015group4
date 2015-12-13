package socialnow.model;

import org.springframework.beans.factory.annotation.Autowired;
import socialnow.dao.UserDao;

/**
 * Created by Erdem on 12/13/2015.
 */
public class Comment_Details {
    private User owner;
    private String comment_text;

    public Comment_Details() {
    }

    public Comment_Details(Comment comm) {
        this.setComment_text(comm.getComment_text());
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }
}
