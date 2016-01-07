package socialnow.rest;

import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.Synoym.*;
import socialnow.Synoym.Synonym;
import socialnow.Utils.RequestSender;
import socialnow.Utils.Util;
import socialnow.dao.EventDao;
import socialnow.dao.Interest_GroupDao;
import socialnow.dao.PostDao;
import socialnow.dao.UserDao;
import socialnow.forms.Search_Form;
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



    @RequestMapping( value = "/synonym", method = RequestMethod.POST)
    public @ResponseBody
    Synonym synonym(@RequestBody String search) throws UnirestException {
        Synonym s = RequestSender.findSynonyms(search);
        s.fillLists();
       return s;

    }

    /**
     *
     * Semantic search applied.
     * the keyword is scanned in the big database of another server by sending a request them.
     *All the new keywords are treated as a new search keyword.
     *
     * @param searchBasic
     * @return
     * @throws UnirestException
     */
    @RequestMapping( value = "/search", method = RequestMethod.POST)
    public @ResponseBody
    SearchReturn search(@RequestBody String searchBasic) throws UnirestException {
        SearchReturn searchReturn = new SearchReturn();
        Search_Form form = gson.fromJson(searchBasic,Search_Form.class);
        searchBasic = form.getKeyword();
        searchBasic = searchBasic.toLowerCase();
        Synonym s = RequestSender.findSynonyms(searchBasic);

        User u = userDao.getByToken(form.getUser_token());

        s.fillLists();
        List<Event> events = eventDao.getAll();
        List<Interest_Group> groups = groupDao.getAll();
        List<User> users = userDao.getAll();





        for (ObjectJson o: s.getResponse()) {
            socialnow.Synoym.List l = o.getList();
            for (String search:l.getSynonymList()) {

                for (Event event : events) {
                    if(!searchReturn.getEvents().contains(event) && event.getEvent_description().contains(search.toLowerCase())  || event.getTitle().toLowerCase().contains(search.toLowerCase()) || event.getTags().contains(search) && Util.canSeeEvent(u,event)){
                        searchReturn.getEvents().add(event);
                    }
                }
                for (User user : users) {
                    if(!searchReturn.getUsers().contains(user) &&user.getSurname().equalsIgnoreCase(search)|| user.getName().equalsIgnoreCase(search) || (user.getName()+" "+user.getSurname()).toLowerCase().contains(search) ) {
                        searchReturn.getUsers().add(user);

                    }
                }
                for (Interest_Group group : groups) {
                    if( !searchReturn.getGroups().contains(group)&& group.getGroup_description().toLowerCase().contains(search.toLowerCase()) || group.getTags().contains(search) && Util.canSeeGroup(u,group)){
                        searchReturn.getGroups().add(group);
                    }
                }




            }



        }


        for (Event event : events) {
            if(!searchReturn.getEvents().contains(event) && event.getEvent_description().contains(searchBasic.toLowerCase())  || event.getTitle().toLowerCase().contains(searchBasic.toLowerCase()) || event.getTags().contains(searchBasic) && Util.canSeeEvent(u,event)){
                searchReturn.getEvents().add(event);
            }
        }
        for (User user : users) {
            if(!searchReturn.getUsers().contains(user) &&user.getSurname().equalsIgnoreCase(searchBasic)|| user.getName().equalsIgnoreCase(searchBasic) || (user.getName()+" "+user.getSurname()).toLowerCase().contains(searchBasic) ) {
                searchReturn.getUsers().add(user);

            }
        }
        for (Interest_Group group : groups) {
            if( !searchReturn.getGroups().contains(group)&& group.getGroup_description().toLowerCase().contains(searchBasic.toLowerCase()) || group.getTags().contains(searchBasic) && Util.canSeeGroup(u,group)){
                searchReturn.getGroups().add(group);
            }
        }




        return searchReturn;
    }


}
