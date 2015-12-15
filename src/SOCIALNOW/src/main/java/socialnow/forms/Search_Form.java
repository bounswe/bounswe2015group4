package socialnow.forms;

/**
 * Created by Erdem on 12/15/2015.
 */
public class Search_Form {

    String user_token;
    String keyword;

    public Search_Form() {
    }

    public String getUser_token() {

        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
