package socialnow.forms;

/**
 * Created by Erdem on 11/26/2015.
 */
public class Interest_Group_Form {

    private String tags = "";
    private String name;
    private String owner_token;

    public Interest_Group_Form(String tags, String name, String owner_token) {
        this.tags = tags;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner_token() {
        return owner_token;
    }

    public void setOwner_token(String owner_token) {
        this.owner_token = owner_token;
    }
}
