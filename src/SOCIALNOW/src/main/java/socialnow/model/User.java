package socialnow.model;

import socialnow.Utils.Error_JSON;
import socialnow.Utils.Util;
import socialnow.forms.User_Form;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

//  private List<Event> events = new ArrayList<Event>() ;

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