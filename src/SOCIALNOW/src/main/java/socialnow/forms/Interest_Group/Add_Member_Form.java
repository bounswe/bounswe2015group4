package socialnow.forms.Interest_Group;

/**
 * Created by Erdem on 12/4/2015.
 */
public class Add_Member_Form {
    String user_token;

    public Add_Member_Form(String user_token, long id) {
      this.user_token = user_token;
        this.interest_group_id = id;
    }

    public Add_Member_Form() {
    }

    long interest_group_id;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public long getId() {
        return interest_group_id;
    }

    public void setId(long id) {
        this.interest_group_id = id;
    }
}
