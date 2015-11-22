package socialnow.forms;

/**
 * Created by mertcan on 22.11.2015.
 */
public class Login_Form {
    String email;
    String password;
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
