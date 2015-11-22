package socialnow.rest;


import com.google.gson.Gson;
import socialnow.Utils.Error_JSON;
import socialnow.Utils.Util;
import socialnow.dao.UserDao;
import socialnow.forms.Login_Form;
import socialnow.forms.User_Form;
import socialnow.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

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
    user_form.setUser_token(Util.generate_token());

    User user = new User(user_form);
      try {
          userDao.create(user);
      }
      catch(Exception e){
          if(e instanceof org.springframework.dao.DataIntegrityViolationException){
              Error_JSON errorJSON = new Error_JSON();
              errorJSON.setCode(8081);
              errorJSON.setMessage("Email exists: " + e.toString());
              return new User(errorJSON);
          }
      }
    return user;
  }

  @RequestMapping( value = "/login", method = RequestMethod.POST)
  public @ResponseBody
  User login(@RequestBody String loginFormData) {
    Login_Form form = gson.fromJson(loginFormData, Login_Form.class);

    User user = userDao.getByEmail(form.getEmail());

    if (!user.getPassword().equals(Util.hash(form.getPassword()))) {
        Error_JSON errorJSON = new Error_JSON();
        errorJSON.setCode(8080);
        errorJSON.setMessage("No user found");
        return new User(errorJSON);
    } else {
        return user;
    }
  }

    @RequestMapping( value = "/edit_user", method = RequestMethod.POST)
    public User edit_user(@RequestBody String formData) {

        User_Form user_form = gson.fromJson(formData, User_Form.class);

        User user = userDao.getByEmail(user_form.getEmail());

        user.merge(user_form);
//
        userDao.update(user);

        return userDao.getByEmail(user.getEmail());
    }

  // Wire the UserDao used inside this controller.
  @Autowired
  private UserDao userDao;

} // class UserController
