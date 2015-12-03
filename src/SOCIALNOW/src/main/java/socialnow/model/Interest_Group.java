package socialnow.model;

import socialnow.forms.Interest_Group_Form;

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

    @Override
    public void setType(String type) {
        this.type=type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "tags", length = 5000)
    private String tags = "";


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

        this.setGroup_events("");
        this.setGroup_members("");
        this.setGroup_name(form.getName());
        this.setGroup_posts("");
    }

    @NotNull

    String owner_token;

    @NotNull
    String group_name;


    @Column(name = "group_members", length = 10000)
    public  String group_members ;

    @Column(name = "group_posts", length = 10000)
    public  String group_posts ;

    @Column(name = "group_events", length = 10000)
    public  String group_events ;

    @Override
    public String toString() {
        return "Interest_Group{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", tags='" + tags + '\'' +
                ", name='" + group_name + '\'' +
                ", group_members='" + group_members + '\'' +
                ", group_posts='" + group_posts + '\'' +
                ", group_events='" + group_events + '\'' +
                '}';
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

    public String getGroup_events() {
        return group_events;
    }

    public void setGroup_events(String group_events) {
        this.group_events = group_events;
    }
}
