package socialnow.rest;


import com.google.gson.Gson;
import socialnow.dao.UserDao;
import socialnow.forms.User_Form;
import socialnow.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Class UserController
 */
@RestController
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  final Gson gson = new Gson();

  @RequestMapping( value = "/signUp", method = RequestMethod.POST)
  public @ResponseBody
  User signUp(@RequestBody String formData) {
    User_Form user_form = gson.fromJson(formData, User_Form.class);
    User user = new User();
    user.setUser(user_form);
    userDao.create(user);
    return user;
  }

  
  // Wire the UserDao used inside this controller.
  @Autowired
  private UserDao userDao;
  
} // class UserController
