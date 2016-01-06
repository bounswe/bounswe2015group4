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
import socialnow.forms.User.User_Token_Form;
import socialnow.model.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
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

    /**
     * finds the most occurrent 3 tags of the user that is sent as a parameter to the request
     * In events and groups finds the events and groups that contain one of these user_tags
     * Finds the all users those are in these events or groups and finds the most common 3 tags of them.
     * Lastly, lists all the groups and events containing these tags
     * @param token
     * @return
     */
    @RequestMapping( value = "/recommend", method = RequestMethod.POST)
    public RecommendReturn recommend(@RequestBody String token) {
        User_Token_Form form = gson.fromJson(token,User_Token_Form.class);
        RecommendReturn suggests = new RecommendReturn();
        User user = userDao.getByToken(form.getUser_token());
        String tags = user.getUser_tags();
        if (tags.contains(",")){
            String [] x =(Util.findMostOccurence(tags.substring(1).split(",")));
            ArrayList<Event>  events = (ArrayList<Event>) eventDao.getAll();
            ArrayList<Interest_Group> groups = (ArrayList<Interest_Group>) groupDao.getAll();
            ArrayList<String[]> twoDtags = new ArrayList<>();
            for (Event e: events) {
                if(e.getTags().contains(x[1])|| e.getTags().contains(x[2])|| e.getTags().contains(x[0])){
                    String [] eventUsers = e.event_participants.split(",");
                    for (int i = 0; i <eventUsers.length ; i++) {
                        if(!eventUsers[i].equals("")){
                            User u= userDao.getByToken(eventUsers[i]);
                            String user_tags = u.getUser_tags();
                            if (user_tags.contains(",")){
                                twoDtags.add(user_tags.substring(1).split(","));
                            }
                        }
                    }
                }
            }
            for (Interest_Group e: groups) {
                if(e.getTags().contains(x[1])|| e.getTags().contains(x[2])|| e.getTags().contains(x[0])){
                    String [] postUSers = e.getGroup_members().split(",");
                    for (int i = 0; i <postUSers.length ; i++) {
                        if(!postUSers[i].equals("")){
                            log.info("");
                            User u= userDao.getByToken(postUSers[i]);
                            String user_tags = u.getUser_tags();
                            if (user_tags.contains(",")){
                                twoDtags.add(user_tags.substring(1).split(","));
                            }
                        }
                    }
                }
            }
            String[][] array= new String [twoDtags.size()+1][250];
            for (int i = 0; i <twoDtags.size() ; i++) {
                for (int j = 0; j < twoDtags.get(i).length; j++) {
                    array[i][j] = twoDtags.get(i)[j];
                }
                array[i][twoDtags.get(i).length] = null;
            }
            array[twoDtags.size()][0] = null;

            String [] recommendedTags = Util.findCommon(array,2);
            events = (ArrayList<Event>) eventDao.getAllForUser(user);
            for (Event e: events) {
                if((e.getTags().contains(recommendedTags[1])|| e.getTags().contains(recommendedTags[0])) && !e.getEvent_participant_users().contains(user.getUser_token()) && !e.getEvent_host_token().contains(user.getUser_token())){
                    suggests.getEvents().add(e);
                }
            }
            groups = (ArrayList<Interest_Group>) groupDao.getAllForUser(user);
            for (Interest_Group e: groups) {
                if((e.getTags().contains(recommendedTags[1])|| e.getTags().contains(recommendedTags[0])) && !e.getGroup_members().contains(user.getUser_token()) && !e.getOwner_token().contains(user.getUser_token())){
                    suggests.getGroup().add(e);

                }
            }
        }else{

            return suggests;
        }

        return suggests;
    }

    /**
     *
     * recent activities of user followed by logged in user within 3 days
     *
     * @param token
     * @return
     */
    @RequestMapping( value = "/timeline", method = RequestMethod.POST)
    public TimelineReturn timeline(@RequestBody String token) { Calendar now = Calendar.getInstance();
        List<Event> allEvents = eventDao.getAll();
        for (Event e:allEvents) {
            if(e.getEvent_end_date().compareTo(now)< 0){
                e.setVisibleTo("non");
                eventDao.update(e);
            }
        }
        User_Token_Form form = gson.fromJson(token,User_Token_Form.class);
        User u = userDao.getByToken(form.getUser_token());
        String followings = u.getUser_followings();
        TimelineReturn timeline = new TimelineReturn();
        Calendar threeDays = Calendar.getInstance();
        threeDays.add(Calendar.DATE,-3);


        if(followings.contains(",")){
            followings = followings.substring(1);
            String[] followingsTokens = followings.split(",");
            for (int i = 0; i <followingsTokens.length; i++) {
                User followed = userDao.getByToken(followingsTokens[i]);
                String groups = followed.getUser_interest_groups();
                if(groups.contains(",")){
                    String[] groupIDs = groups.substring(1).split(",");
                    for (int j = 0; j <groupIDs.length ; j++) {
                        Interest_Group group = groupDao.getById( groupIDs[j]);
                        if(group.getTs().compareTo(threeDays) > 0 && Util.canSeeGroup(u,group)){
                            User_Group ug = new User_Group(group,followed);
                            timeline.getUser_groups().add(ug);
                        }
                    }
                }
                String events  = followed.getUser_participating_events();
                if(events.contains(",")){
                    String[] eventId = events.substring(1).split(",");
                    for (int j = 0; j <eventId.length; j++) {
                        Event event = eventDao.getById( eventId[j]);
                        if(event.getTs().compareTo(threeDays) > 0 && Util.canSeeEvent(u,event)){
                            User_Event ue = new User_Event(followed,event);
                            timeline.getUser_events().add(ue);
                        }
                    }
                }
            }
        }
        else {
            return timeline;
        }
        return timeline;

    }

    @RequestMapping( value = "/recommendUsers", method = RequestMethod.POST)
    public ArrayList<User> recommendPeople(@RequestBody String token){
        HashMap<Integer, User> map = new HashMap<>();
        ArrayList<User> allUsers = (ArrayList<User>) userDao.getAll();
        User_Token_Form form = gson.fromJson(token,User_Token_Form.class);
        User u = userDao.getByToken(form.getUser_token());
        if(!u.getUser_tags().contains(",")){
            Collections.sort(allUsers);
            return (ArrayList<User>) allUsers.subList(0,10);
        }
        ArrayList<User> suggestUser = new ArrayList<>();
        for (User user: allUsers) {
            if(user.getUser_tags().contains(",")) {
                int commonality = Util.calculateResemblance(u.getUser_tags().substring(1).split(","),user.getUser_tags().substring(1).split(","));
                if(map.containsKey(commonality)) {
                    if (map.get(commonality).numberOfFollowers < user.numberOfFollowings)
                        map.put(commonality, user);
                }
                else {
                    map.put(commonality, user);
                }

            }
        }
        ArrayList<Integer> keys = new ArrayList<>();
        keys.addAll(map.keySet());
        Collections.sort(keys);
        int maxToSugest = 10;
        if(keys.size() < maxToSugest)
            maxToSugest = keys.size();
        for (int i = 0; i <maxToSugest; i++) {
            suggestUser.add(map.get(keys.get(i)));

        }
        return suggestUser;
    }




}
