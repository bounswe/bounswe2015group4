package socialnow.rest;

import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.dao.EventDao;
import socialnow.dao.Interest_GroupDao;
import socialnow.dao.PostDao;
import socialnow.dao.UserDao;
import socialnow.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erdem on 11/27/2015.
 */
@RestController
public class SearchController {


    final Gson gson = new Gson();

    @Autowired
    private EventDao eventDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private Interest_GroupDao groupDao;


    @RequestMapping( value = "/search", method = RequestMethod.POST)
    public @ResponseBody
    SearchReturn search(@RequestBody String search) throws UnirestException {
      SearchReturn searchReturn = new SearchReturn();
        List<Event> events = eventDao.getAll();
        List<Interest_Group> groups = groupDao.getAll();
        List<User> users = userDao.getAll();
        for (Event event : events) {
            if(event.getTitle().toLowerCase().contains(search.toLowerCase()) || event.getTags().contains(search) ){
                searchReturn.getEvents().add(event);
            }
        }
        for (User user : users) {
            if(user.getSurname().equalsIgnoreCase(search)|| user.getName().equalsIgnoreCase(search)  )  {
             searchReturn.getUsers().add(user);

            }
        }
        for (Interest_Group group : groups) {
            if(group.getGroup_description().toLowerCase().contains(search.toLowerCase()) || group.getTags().contains(search)){
              searchReturn.getGroups().add(group);
            }
        }


return searchReturn;
    }


}
