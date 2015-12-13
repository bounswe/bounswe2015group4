package socialnow.forms.Comment;

/**
 * Created by Erdem on 12/13/2015.
 *
 *
 */
public class Create_Comment_Form {

    public Create_Comment_Form(String owner_token, String comment_text) {
        this.owner_token = owner_token;
        this.comment_text = comment_text;
    }

    @Override
    public String toString() {
        return "Create_Comment_Form{" +
                "owner_token='" + owner_token + '\'' +
                ", comment_text='" + comment_text + '\'' +
                '}';
    }

    public Create_Comment_Form() {
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

    private String owner_token;
    private String comment_text;





}
