package socialnow.model;

/**
 *This model defines the user groups
 *@author Erdem
 * @param group shows the interest groups
 * @param user defines the user belong to a group
 */
public class User_Group {

    User user;
    Interest_Group group;

    public User_Group(Interest_Group group, User user) {
        this.group = group;
        this.user = user;
    }
/**
*Constructor
*/
    public User_Group() {

    }
/**
*
*@return current user
*/
    public User getUser() {

        return user;
    }
/**
 * 
 * @param user user to set
 */
    public void setUser(User user) {
        this.user = user;
    }
/**
*
*@return current group
*/
    public Interest_Group getGroup() {
        return group;
    }
/**
 * 
 * @param group group to set
 */
    public void setGroup(Interest_Group group) {
        this.group = group;
    }
}
