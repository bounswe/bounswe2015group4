package socialnow.model;

import socialnow.forms.Comment.Create_Comment_Form;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Erdem on 12/13/2015.
 */
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String owner_token;

    @NotNull
    private String comment_text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Comment() {
    }

    public String getOwner_token() {
        return owner_token;
    }

    public void setOwner_token(String owner_token) {
        this.owner_token = owner_token;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Comment(Create_Comment_Form form){
        this.setComment_text(form.getComment_text());
        this.setOwner_token(form.getOwner_token());
    }
}
