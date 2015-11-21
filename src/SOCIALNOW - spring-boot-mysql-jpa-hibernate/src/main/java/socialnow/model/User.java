package socialnow.model;

import socialnow.forms.User_Form;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
  
  @NotNull
  private String email;

  @NotNull
  private String password;

  @NotNull
  private String name;

  @NotNull
  private String surname;

  @NotNull
  private String role;

//  private List<Event> events = new ArrayList<Event>() ;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  public User() { }

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

  public void setUser(User_Form u_f){
      name = u_f.getName();
      password = u_f.getPassword();
      email = u_f.getEmail();
      role = u_f.getRole();
      surname = u_f.getSurname();
  }
  
} // class User
