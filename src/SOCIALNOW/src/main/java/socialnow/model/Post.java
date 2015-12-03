package socialnow.model;

import socialnow.Utils.Error_JSON;
import socialnow.forms.Post_Form;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Erdem on 11/23/2015.
 */
@Entity
@Table(name = "posts")
public class Post  implements SearchReturn  {

    public String getType() {
        return type;
    }
    @Transient
    String type;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String owner_token;

    @NotNull
    private String content;


    @Column(name = "post_comments")
    private String post_comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner_token() {
        return owner_token;
    }

    public void setOwner_token(String owner_token) {
        this.owner_token = owner_token;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPost_comments() {
        return post_comments;
    }

    public void setPost_comments(String post_comments) {
        this.post_comments = post_comments;
    }

    public Post(Post_Form form){
        this.setContent(form.getContent());
        this.setOwner_token(form.getOwner_token());
        this.setPost_comments(form.getPost_comments());

    }

    public Post(Error_JSON e) {
        id = -1;
        content = e.toString();
    }
    public Post() {
    }

    @Override
    public void setType(String type) {
        this.type=type;
    }
}
