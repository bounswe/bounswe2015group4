package socialnow.forms.Interest_Group;

/**
 * Created by Erdem on 12/4/2015.
 */
public class Add_Post_Form {

    int interest_group_id;
    int post_id;

    public Add_Post_Form() {
    }

    public Add_Post_Form(int interes_group_id, int post_id) {

        this.interest_group_id = interes_group_id;
        this.post_id = post_id;
    }

    public int getInterest_group_id() {
        return interest_group_id;
    }

    public void setInterest_group_id(int interes_group_id) {
        this.interest_group_id = interes_group_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
}
