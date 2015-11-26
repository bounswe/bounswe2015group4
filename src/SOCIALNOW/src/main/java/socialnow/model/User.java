package socialnow.model;

import socialnow.Utils.Error_JSON;
import socialnow.Utils.Util;
import socialnow.forms.User_Form;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Represents an User for this web application.
 */
@Entity
@Table(name = "users")
public class User {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String role;

    @Column(name = "user_token", unique = true)
    private String user_token;

    public String getUser_participating_events() {
        return user_participating_events;
    }

    public void setUser_participating_events(String user_participating_events) {
        this.user_participating_events = user_participating_events;
    }

    @Column(name = "user_participating_events", length = 2000)
    private String user_participating_events ;

//  private List<Event> user_participating_events

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
        this.user_participating_events = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String value) {
        this.surname = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_token() {
        return user_token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                ", user_token='" + user_token + '\'' +
                ", user_participating_events=" + user_participating_events +
                '}';
    }

    public void setUser_token(String token) {
        this.user_token = token;
    }

    public User(User_Form u_f) {
        name = u_f.getName();
        password = Util.hash(u_f.getPassword());
        email = u_f.getEmail();
        role = u_f.getRole();
        surname = u_f.getSurname();
        user_token = u_f.getUser_token();
        this.user_participating_events = "";
    }

    public User(Error_JSON e) {
        id = -1;
        user_token = e.toString();
    }

    public void merge(User_Form u_f){
        if(u_f.getName() != null)
            name = u_f.getName();
        if(u_f.getPassword() != null)
            password = Util.hash(u_f.getPassword());
        if(u_f.getRole() != null)
           role = u_f.getRole();
        if(u_f.getSurname() != null)
            surname = u_f.getSurname();
    }

} // class User
