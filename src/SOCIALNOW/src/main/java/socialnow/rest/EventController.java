package socialnow.rest;

import com.google.gson.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.SemanticTagging.SemanticResponse;
import socialnow.Utils.Error_JSON;
import socialnow.Utils.RequestSender;
import socialnow.Utils.Util;
import socialnow.dao.EventDao;
import socialnow.dao.UserDao;
import socialnow.forms.Add_Post_Event_Form;
import socialnow.forms.Event_Form;
import socialnow.forms.User_Token_Form;
import socialnow.model.Event;
import socialnow.model.SearchReturn;
import socialnow.model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


@RestController
public class EventController {
    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;
    Logger log = Logger.getLogger("EVENTCONTROLLER");
    // Creates the json object which will manage the information received
    GsonBuilder builder = new GsonBuilder();

// Register an adapter to manage the date types as long values


    Gson gson = new GsonBuilder()
            .setDateFormat("dd/MM/yyyy")
            .create();
    @RequestMapping( value = "/createEvent", method = RequestMethod.POST)
    public @ResponseBody
    Event addEvent(@RequestBody String addEventForm) {
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        gson = builder.create();

        Event_Form form = gson.fromJson(addEventForm, Event_Form.class);
        Event e = new Event(form);
        User u = userDao.getByToken(e.getEvent_host_token());
        u.setUser_tags(u.getUser_tags() + e.getTags());
        eventDao.create(e);
        return e;
    }

    @RequestMapping( value = "/events/addPost", method = RequestMethod.POST)
    public @ResponseBody
    Event addPost(@RequestBody String addPostEventForm) {

        Add_Post_Event_Form apef = gson.fromJson(addPostEventForm, Add_Post_Event_Form.class);
        Event e = eventDao.getById(apef.getEvent_id());
        e.setEvent_posts(e.getEvent_posts()+","+ apef.getPost_id());
        return e;
    }





    @RequestMapping( value = "/deneme", method = RequestMethod.POST)
    public @ResponseBody
    SemanticResponse FOOO(@RequestBody String search) throws UnirestException {
        return  RequestSender.searchSemantics(search);
    }

    @RequestMapping(value = "/events/addParticipant", method = RequestMethod.POST)
    public @ResponseBody Event addParticipant(@RequestBody String token){
        User_Token_Form form = gson.fromJson(token,User_Token_Form.class);
        User user =userDao.getByToken(form.getUser_token());
        Event event =  eventDao.getById(form.getEvent_id());
        if(Util.arrayContains(user.getUser_participating_events().split(","), event.getId()+"")){
            Error_JSON errorJSON = new Error_JSON();
            errorJSON.setCode(8082);
            errorJSON.setMessage("This user is already participating to this event ");
            return new Event(errorJSON);
        }
        user.setUser_participating_events(user.getUser_participating_events()+"," + form.getEvent_id());
        event.event_participants= (event.event_participants+"," + form.getUser_token());

        log.info(user.getUser_tags());
        log.info(event.getTags());
        user.setUser_tags(user.getUser_tags() + event.getTags());
        try{
            userDao.update(user);
            eventDao.update(event);

        }catch (Exception e) {
            Error_JSON errorJSON = new Error_JSON();
            errorJSON.setCode(8081);
            errorJSON.setMessage("DATABASE ERROR: " + e.toString());
            return new Event(errorJSON);
        }
        return  this.fillEvent(event);
    }



    @RequestMapping(value = "/events/removeParticipant", method = RequestMethod.POST)
    public @ResponseBody Event removeParticipant(@RequestBody String token){
        User_Token_Form form = gson.fromJson(token,User_Token_Form.class);
        User user =userDao.getByToken(form.getUser_token());
        Event event =  eventDao.getById(form.getEvent_id());
        log.info(event.getId()+"");
        log.info(user.getUser_participating_events());
        if(!Util.arrayContains(user.getUser_participating_events().split(","), event.getId()+"")){
            Error_JSON errorJSON = new Error_JSON();
            errorJSON.setCode(8082);
            errorJSON.setMessage("This user is not  participating to this event ");
            return new Event(errorJSON);
        }
        log.info(Util.deleteFromArray(user.getUser_participating_events().split(","),event.getId()+""));
        user.setUser_participating_events(Util.deleteFromArray(user.getUser_participating_events().split(","),event.getId()+""));
        event.event_participants = ( Util.deleteFromArray( event.event_participants.split(","), form.getUser_token()));
        user.setUser_tags(Util.deleteFromArray(user.getUser_tags().split(","),   event.getTags().split(",")));
        try{
            userDao.update(user);
            eventDao.update(event);

        }catch (Exception e) {
            Error_JSON errorJSON = new Error_JSON();
            errorJSON.setCode(8081);
            errorJSON.setMessage("DATABASE ERROR:" + e.toString());
            return new Event(errorJSON);

        }


        return  this.fillEvent(event);

    }

    @RequestMapping( value = "/listAllEvents", method = RequestMethod.POST)
    public @ResponseBody
    List<Event> listAllEvents(){
        List<Event> eventList  = eventDao.getAll();
        List<Event>    eventlistFilled = new ArrayList<Event>();
        for (Event event :
                eventList) {
            eventlistFilled.add(this.fillEvent(event));
        }




        String[][] tags = new String[5][20];
        tags[0][0]="java";
        tags[0][1]="science fiction";
        tags[0][2]="music";
        tags[1][0]="book";
        tags[1][1]="music";
        tags[1][2]="c++";
        tags[1][3]="love stories";
        tags[1][4]="c++";
        tags[1][5]="c++";
        tags[1][6]="body building";
        tags[1][7]="body building";
        tags[2][0]="book";
        tags[2][1]="java";
        tags[2][2]="java";

        String[] result=Util.find_common(tags,1);
        for(int i=0;i<result.length;i++)
            log.info(result[i]);




        return  eventlistFilled;
    }

    @RequestMapping( value = "/listMyEvents", method = RequestMethod.POST)
    public @ResponseBody
    List<Event> listEventOfUser(@RequestBody String token){
        Event_Form form = gson.fromJson(token, Event_Form.class);
        List<Event> eventList  = eventDao.getAllByToken(form.getEvent_host_token());
        List<Event>    eventlistFilled = new ArrayList<Event>();
        for (Event event :
                eventList) {
            eventlistFilled.add(this.fillEvent(event));
        }
        return  eventlistFilled;
    }

    @RequestMapping( value = "/listAttendingEvents", method = RequestMethod.POST)
    public @ResponseBody
    List<Event> listEventsParticipated(@RequestBody String token){
        List<Event> eventList = new ArrayList<Event>();
        User_Token_Form form = gson.fromJson(token, User_Token_Form.class);
        User user = userDao.getByToken(form.getUser_token());
        log.info((user.getUser_participating_events()));
        if(user.getUser_participating_events()=="")
            return new ArrayList<Event>();
        String[] eventIdArray = user.getUser_participating_events().split(",");


        for (int i = 0; i <eventIdArray.length; i++) {

            try {
                int id = Integer.parseInt(eventIdArray[i]);
                Event e = eventDao.getById(id);
                eventList.add( this.fillEvent(e));
            }catch (Exception e){


            }


        }

        return eventList;
    }

    public  Event fillEvent(Event event){
        String [] participantId = event.event_participants.split(",");
        List<User> users = new ArrayList<User>();
        for (int i = 0; i <participantId.length ; i++) {
            if (!participantId[i].equals("")) {

                users.add(userDao.getByToken(participantId[i]));
            }
        }
        event.fillUsers(users);

        return event;
    }

}
