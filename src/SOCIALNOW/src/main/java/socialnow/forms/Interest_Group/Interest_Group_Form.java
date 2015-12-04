package socialnow.forms.Interest_Group;

/**
 * Created by Erdem on 11/26/2015.
 */
public class Interest_Group_Form {

    private String tags = "";
    private String group_name;
    private String owner_token;
    private String group_description;

    public String getGroup_photo() {
        return group_photo;
    }

    public void setGroup_photo(String group_photo) {
        this.group_photo = group_photo;
    }

    private String group_photo;

    public String getGroup_description() {
        return group_description;
    }

    public void setGroup_description(String group_description) {
        this.group_description = group_description;
    }

    public Interest_Group_Form(String tags, String group_name, String owner_token) {
        this.tags = tags;
        this.group_name = group_name;
        this.owner_token = owner_token;
    }

    public Interest_Group_Form() {

    }

    public String getTags() {

        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getOwner_token() {
        return owner_token;
    }

    public void setOwner_token(String owner_token) {
        this.owner_token = owner_token;
    }


}
