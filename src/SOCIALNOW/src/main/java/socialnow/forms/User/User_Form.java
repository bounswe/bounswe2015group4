package socialnow.forms.User;

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
    String user_photo;

    @Override
    public String toString() {
        return "User_Form{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", user_token='" + user_token + '\'' +
                ", user_photo='" + user_photo + '\'' +
                '}';
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
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

    public String getUser_photo() {
        return user_photo;
    }
}
