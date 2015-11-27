package socialnow.rest;

import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.dao.EventDao;
import socialnow.dao.PostDao;
import socialnow.dao.UserDao;
import socialnow.model.Event;
import socialnow.model.Post;
import socialnow.model.SearchReturn;
import socialnow.model.User;

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


    @RequestMapping( value = "/search", method = RequestMethod.POST)
    public @ResponseBody
    List<SearchReturn> search(@RequestBody String search) throws UnirestException {
        List<SearchReturn> searchReturns = new ArrayList<SearchReturn>();
        List<Event> events = eventDao.getAll();
        List<Post> posts = postDao.getAll();
        List<User> users = userDao.getAll();
        for (Event event : events) {
            if(event.getTitle().toLowerCase().contains(search.toLowerCase())){
                event.setType("event");
                searchReturns.add(event);
            }
        }
        for (User user : users) {
            if(user.getSurname().equalsIgnoreCase(search)|| user.getName().equalsIgnoreCase(search)){
                user.setType("user");
                searchReturns.add(user);
            }
        }
        for (Post post : posts) {
            if(post.getContent().toLowerCase().contains(search.toLowerCase())){
                post.setType("post");
                searchReturns.add(post);
            }
        }


return searchReturns;
    }


}
