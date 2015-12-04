package socialnow.model;

import socialnow.Utils.Error_JSON;
import socialnow.forms.Interest_Group.Interest_Group_Form;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Erdem on 12/3/2015.
 */
@Entity
@Table(name = "interest_groups")
public class Interest_Group implements SearchReturn{

    @Transient
    String type;

    public Interest_Group(Error_JSON e) {
        id = -1;
        group_name = e.toString();
    }

    @Override
    public void setType(String type) {
        this.type=type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "tags", length = 5000)
    private String tags = "";


    @NotNull
    String group_description;
    @NotNull
    String owner_token;

    @NotNull
    String group_name;


    @Column(name = "group_members", length = 10000)
    public  String group_members ;

    @Column(name = "group_posts", length = 10000)
    public  String group_posts ;

    public String getGroup_photo() {
        return group_photo;
    }

    public void setGroup_photo(String group_photo) {
        this.group_photo = group_photo;
    }

    @Column(name = "group_photo")
    public  String group_photo ;


    @Override
    public String toString() {
        return "Interest_Group{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", tags='" + tags + '\'' +
                ", group_description='" + group_description + '\'' +
                ", owner_token='" + owner_token + '\'' +
                ", group_name='" + group_name + '\'' +
                ", group_members='" + group_members + '\'' +
                ", group_posts='" + group_posts + '\'' +
                '}';
    }

    public String getGroup_description() {
        return group_description;
    }

    public void setGroup_description(String group_description) {
        this.group_description = group_description;
    }


    public String getOwner_token() {
        return owner_token;
    }

    public void setOwner_token(String owner_token) {
        this.owner_token = owner_token;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Interest_Group() {
    }

    public Interest_Group(Interest_Group_Form form) {
        this.owner_token = form.getOwner_token();
        if (form.getTags().startsWith(",")){
            this.tags = form.getTags();
        }
        else{
            this.tags = ","+form.getTags();
        }
        this.group_description=form.getGroup_description();
        this.setGroup_members("");
        this.setGroup_name(form.getGroup_name());
        this.setGroup_posts("");
        this.setGroup_photo(form.getGroup_photo());
    }


    public String getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getGroup_members() {
        return group_members;
    }

    public void setGroup_members(String group_members) {
        this.group_members = group_members;
    }

    public String getGroup_posts() {
        return group_posts;
    }

    public void setGroup_posts(String group_posts) {
        this.group_posts = group_posts;
    }

}
