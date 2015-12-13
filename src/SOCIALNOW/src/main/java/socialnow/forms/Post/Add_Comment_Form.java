package socialnow.forms.Post;

/**
 * Created by Erdem on 12/13/2015.
 */
public class Add_Comment_Form {
    int post_id;
    int comment_id;

    public Add_Comment_Form() {
    }

    public int getComment_id() {

        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
}
