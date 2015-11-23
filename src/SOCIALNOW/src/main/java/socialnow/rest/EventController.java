package socialnow.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.dao.EventDao;
import socialnow.dao.UserDao;
import socialnow.forms.Event_Form;
import socialnow.model.Event;
import socialnow.model.User;

import java.util.List;

/**
 * Created by mertcan on 22.11.2015.
 */
@RestController
public class EventController {
    @Autowired
    private EventDao eventDao;

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


    @RequestMapping( value = "/listAllEvents", method = RequestMethod.POST)
    public @ResponseBody
    List<Event> listAllEvents(){
        List<Event> eventList  = eventDao.getAll();
        return  eventList;
    }

    @RequestMapping( value = "/listEvents", method = RequestMethod.POST)
    public @ResponseBody
    List<Event> listEventOfUser(@RequestBody String token){
        Event_Form form = gson.fromJson(token, Event_Form.class);
        List<Event> eventList  = eventDao.getAllByToken(form.getEvent_host_token());
        return  eventList;
    }


}
