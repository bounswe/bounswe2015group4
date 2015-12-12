package socialnow.rest;


import com.google.gson.Gson;
import socialnow.Utils.Error_JSON;
import socialnow.Utils.Util;
import socialnow.dao.EventDao;
import socialnow.dao.Interest_GroupDao;
import socialnow.dao.PostDao;
import socialnow.dao.UserDao;
import socialnow.forms.User.Follow_User_Form;
import socialnow.forms.User.Login_Form;
import socialnow.forms.User.User_Form;
import socialnow.forms.User.User_Token_Form;
import socialnow.model.Event;
import socialnow.model.Interest_Group;
import socialnow.model.Profile;
import socialnow.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    Logger log = Logger.getLogger("USERCONTROLLER");

    @RequestMapping( value = "/signUp", method = RequestMethod.POST)
    public @ResponseBody
    User signUp(@RequestBody String formData) {

        User_Form user_form = gson.fromJson(formData, User_Form.class);
        user_form.setUser_token(Util.generate_token());
        log.info(user_form.toString());
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
        userDao.update(user);

        return userDao.getByEmail(user.getEmail());
    }



    @RequestMapping( value = "/showProfileDetails", method = RequestMethod.POST)
    public Profile showProfileDetails(@RequestBody String userToken) {

        User_Token_Form form = gson.fromJson(userToken, User_Token_Form.class);
        User u = userDao.getByToken(form.getUser_token());
        log.info(u.toString());
        Profile p = new Profile(u);
        String tag = u.getUser_tags();
        if(tag.contains(",")) {  // TAGS
            ArrayList<String > tags= new ArrayList<String>(Arrays.asList(tag.substring(1).split(",")));
            p.setUser_tags(tags);
        }

        String[] arr = u.getUser_interest_groups().split(",");
        ArrayList<Interest_Group> igs= new ArrayList<>();
        for (int i = 0; i <arr.length ; i++) {
            if(!arr[i].equals(""))
            {
                Interest_Group ig = groupDao.getById(arr[i]);
                igs.add(ig);
            }

        }
        p.setUser_interest_groups(igs);

        arr = u.getUser_participating_events().split(",");
        ArrayList<Event> events= new ArrayList<>();
        for (int i = 0; i <arr.length ; i++) {
            if(!arr[i].equals(""))
            {
                Event ig = eventDao.getById(arr[i]);
                events.add(ig);
            }

        }
        p.setUser_participating_events(events);
        arr = u.getUser_followings().split(",");
        log.info(u.getUser_followings()+"          asdasdsd");
        ArrayList<User> usersFollowing= new ArrayList<>();
        for (int i = 0; i <arr.length ; i++) {
            if(!arr[i].equals("")){
                User ig = userDao.getByToken(arr[i]);
                usersFollowing.add(ig);
            }
        }

        p.setUser_following(usersFollowing);
        ArrayList<User> usersFollower= new ArrayList<>();

        arr = u.getUser_followers().split(",");
        for (int i = 0; i <arr.length ; i++) {
            if(!arr[i].equals("")){
                User ig = userDao.getByToken(arr[i]);
                usersFollower.add(ig);
            }
        }
        p.setUser_followers(usersFollower);
        return p;
    }












    @RequestMapping( value = "/followUser", method = RequestMethod.POST)
    public User followUser(@RequestBody String followUSerForm) {
        Follow_User_Form fuf = gson.fromJson(followUSerForm, Follow_User_Form.class);
        User u1 = userDao.getByToken(fuf.getUser_token());
        User u2 = userDao.getByToken(fuf.getUser_token_follow());
        u1.numberOfFollowings++;
        u2.numberOfFollowers++;
        u1.setUser_followings(u1.getUser_followings()+","+ u2.getUser_token());
        u2.setUser_followers(u2.getUser_followers()+ ","+ u1.getUser_token());
        userDao.update(u1);
        userDao.update(u2);
        return u1;
    }

    @RequestMapping( value = "/unFollowUser", method = RequestMethod.POST)
    public User unFollowUser(@RequestBody String followUSerForm) {
        Follow_User_Form uuf = gson.fromJson(followUSerForm, Follow_User_Form.class);
        User u1 = userDao.getByToken(uuf.getUser_token());
        User u2 = userDao.getByToken(uuf.getUser_token_follow());
        u1.numberOfFollowings--;
        u2.numberOfFollowers--;
        u1.setUser_followings(Util.deleteFromArray(u1.getUser_followings().split(","),u2.getUser_token()));
        u2.setUser_followers(Util.deleteFromArray(u2.getUser_followers().split(","),u1.getUser_token()));
        userDao.update(u1);
        userDao.update(u2);
        return u1;
    }






    // Wire the UserDao used inside this controller.
    @Autowired
    private UserDao userDao;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private PostDao postDao;
    @Autowired
    private Interest_GroupDao groupDao;

} // class UserController
