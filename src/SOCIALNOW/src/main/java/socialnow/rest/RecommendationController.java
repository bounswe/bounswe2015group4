package socialnow.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import socialnow.Utils.Util;
import socialnow.dao.EventDao;
import socialnow.dao.Interest_GroupDao;
import socialnow.dao.PostDao;
import socialnow.dao.UserDao;
import socialnow.forms.User.User_Form;
import socialnow.forms.User.User_Token_Form;
import socialnow.model.Event;
import socialnow.model.Interest_Group;
import socialnow.model.SearchReturn;
import socialnow.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Erdem on 12/4/2015.
 */
@RestController
public class RecommendationController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private PostDao postDao;
    @Autowired
    private Interest_GroupDao groupDao;
    Gson gson = new GsonBuilder()
            .setDateFormat("dd/mm/yyyy")
            .create();
    Logger log = Logger.getLogger("Recommendation Controller");

    @RequestMapping( value = "/recommend", method = RequestMethod.POST)
    public ArrayList<SearchReturn> edit_user(@RequestBody String token) {
        User_Token_Form form = gson.fromJson(token,User_Token_Form.class);
        ArrayList<SearchReturn> suggests = new ArrayList<>();
        User user = userDao.getByToken(form.getUser_token());
        String tags = user.getUser_tags();
        if (tags.contains(",")){
            String [] x =(Util.findMostOccurence(tags.substring(1).split(",")));
            ArrayList<Event>  events = (ArrayList<Event>) eventDao.getAll();
            ArrayList<Interest_Group> groups = (ArrayList<Interest_Group>) groupDao.getAll();

            for (Event e: events) {


            }
            for (Interest_Group g: groups) {



            }







        }else{

            return suggests;
        }

return suggests;
    }




}
