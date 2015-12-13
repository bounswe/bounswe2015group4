package socialnow.model;


import java.util.List;

/**
 * Created by Erdem on 12/4/2015.
 */
public class PostDetail {
    private long id;
    private User owner;
    private String content;
    private List<Comment_Details> comments;

    public PostDetail() {
    }
    public PostDetail(Post post) {
        this.id=post.getId();
        this.content=post.getContent();
    }


    public PostDetail(long id, User owner, String content) {

        this.id = id;
        this.owner = owner;
        this.content = content;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment_Details> getComments() {
        return comments;
    }

    public void setComments(List<Comment_Details> comments) {
        this.comments = comments;
    }
}
