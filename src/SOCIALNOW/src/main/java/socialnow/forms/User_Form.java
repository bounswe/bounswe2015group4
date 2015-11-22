package socialnow.forms;

/**
 * Created by mertcan on 22.11.2015.
 */
public class User_Form {
    String email;
    String password;
    String role;
    String name;
    String surname;
    String user_token;

    @Override
    public String toString() {
        return "SignUpForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String token) {
        this.user_token = token;
    }

}
