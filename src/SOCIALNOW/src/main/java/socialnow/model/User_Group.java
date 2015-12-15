package socialnow.model;

/**
 * Created by Erdem on 12/15/2015.
 */
public class User_Group {

    User user;
    Interest_Group group;

    public User_Group(Interest_Group group, User user) {
        this.group = group;
        this.user = user;
    }

    public User_Group() {

    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Interest_Group getGroup() {
        return group;
    }

    public void setGroup(Interest_Group group) {
        this.group = group;
    }
}
