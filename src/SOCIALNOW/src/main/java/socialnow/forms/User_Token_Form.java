package socialnow.forms;

/**
 * Created by Erdem on 11/23/2015.
 */
public class User_Token_Form {

    public User_Token_Form(String user_token) {
        this.user_token = user_token;
    }

    @Override
    public String toString() {
        return "User_Token_Form{" +
                "user_token='" + user_token + '\'' +
                '}';
    }

    private String user_token;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }
}
