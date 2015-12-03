package socialnow.forms;

/**
 * Created by Erdem on 11/23/2015.
 */
public class Post_Form {

    private String content;
    private String post_comments;
    private String owner_token;

    @Override
    public String toString() {
        return "Post_Form{" +
                "content='" + content + '\'' +
                ", comments='" + post_comments + '\'' +
                ", owner_token='" + owner_token + '\'' +
                '}';
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

    public void setPost_comments(String comments) {
        this.post_comments = comments;
    }

    public String getOwner_token() {
        return owner_token;
    }

    public void setOwner_token(String owner_token) {
        this.owner_token = owner_token;
    }

}
