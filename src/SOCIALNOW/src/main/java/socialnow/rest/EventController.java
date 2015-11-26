package socialnow.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.Utils.Error_JSON;
import socialnow.Utils.Util;
import socialnow.dao.EventDao;
import socialnow.dao.UserDao;
import socialnow.forms.Event_Form;
import socialnow.forms.User_Token_Form;
import socialnow.model.Event;
import socialnow.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@RestController
public class EventController {
    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;
    Logger log = Logger.getLogger("EVENTCONTROLLER");
    Gson gson = new GsonBuilder()
            .setDateFormat("dd/MM/yyyy")
            .create();
    @RequestMapping( value = "/createEvent", method = RequestMethod.POST)
    public @ResponseBody
    Event addEvent(@RequestBody String addEventForm) {
        Event_Form form = gson.fromJson(addEventForm, Event_Form.class);
        Event e = new Event(form);
        eventDao.create(e);
        return e;
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
        event.setEvent_participants(event.getEvent_participants()+"," + form.getUser_token());
        try{
            userDao.update(user);
            eventDao.update(event);

        }catch (Exception e) {
            Error_JSON errorJSON = new Error_JSON();
            errorJSON.setCode(8081);
            errorJSON.setMessage("DATABASE ERROR: " + e.toString());
            return new Event(errorJSON);

        }

        return  event;

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
        event.setEvent_participants( Util.deleteFromArray( event.getEvent_participants().split(","), form.getUser_token()));
        try{
            userDao.update(user);
            eventDao.update(event);

        }catch (Exception e) {
            Error_JSON errorJSON = new Error_JSON();
            errorJSON.setCode(8081);
            errorJSON.setMessage("DATABASE ERROR:" + e.toString());
            return new Event(errorJSON);

        }

        return  event;

    }






    @RequestMapping( value = "/listAllEvents", method = RequestMethod.POST)
    public @ResponseBody
    List<Event> listAllEvents(){
        List<Event> eventList  = eventDao.getAll();
        return  eventList;
    }

    @RequestMapping( value = "/listMyEvents", method = RequestMethod.POST)
    public @ResponseBody
    List<Event> listEventOfUser(@RequestBody String token){
        Event_Form form = gson.fromJson(token, Event_Form.class);
        List<Event> eventList  = eventDao.getAllByToken(form.getEvent_host_token());
        return  eventList;
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
                eventList.add(e);
            }catch (Exception e){


            }


        }

        return eventList;
    }


}
