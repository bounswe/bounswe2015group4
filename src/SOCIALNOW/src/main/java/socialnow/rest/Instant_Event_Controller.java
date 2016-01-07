package socialnow.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.dao.*;
import socialnow.forms.Event.Instant_Event_Form;
import socialnow.model.Instant_Event;
import socialnow.model.Instant_Event_Details;
import socialnow.model.User;
import sun.util.resources.cldr.aa.CalendarData_aa_ER;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Erdem on 12/12/2015.
 */

@RestController
public class Instant_Event_Controller {
    @Autowired
    private Instant_EventDao instant_eventDao;
    @Autowired
    private Interest_GroupDao groupDao;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PostDao postDao;
    Gson gson = new GsonBuilder()
            .setDateFormat("dd/mm/yyyy")
            .create();

    Logger log = Logger.getLogger("USERCONTROLLER");

    @RequestMapping( value = "/createInstantEvent", method = RequestMethod.POST)
    public @ResponseBody
    Instant_Event addEvent(@RequestBody String addEventForm) {
        Instant_Event_Form form = gson.fromJson(addEventForm, Instant_Event_Form.class);
        Instant_Event e = new Instant_Event(form);
        instant_eventDao.create(e);
        return e;
    }


    @RequestMapping( value = "/getInstantEvent", method = RequestMethod.POST)
    public @ResponseBody
    List<Instant_Event_Details> getInstantEvent(@RequestBody String addEventForm) {
        List<Instant_Event_Details> result = new ArrayList<>();
        List<Instant_Event> events =  instant_eventDao.getAll();
        deleteInstantEvents(events);
        events =  instant_eventDao.getAll();
        for (Instant_Event e: events) {
           long diff = Calendar.getInstance().getTimeInMillis() - e.getDate().getTimeInMillis();
            log.info(diff+" AAAAAAAAAAAAAAAAAAAAAAAAAa");
            int diff_in_mins = e.getDuration_in_minutes()- (int) ( diff / (60 * 1000));
            User u = userDao.getByToken(e.getInstant_event_owner());
            Instant_Event_Details ie = new Instant_Event_Details(e);
            ie.setInstant_event_owner(u);
            ie.setTime_remaining( diff_in_mins);

            result.add(ie);
        }
        return result;
    }

public void deleteInstantEvents(List<Instant_Event> events){
    for (Instant_Event e: events) {
        e.getDate().add(Calendar.MINUTE, e.getDuration_in_minutes());
        if(e.getDate().compareTo( Calendar.getInstance()) < 0 ){
            instant_eventDao.delete(e);
        }else {
            e.getDate().add(Calendar.MINUTE, e.getDuration_in_minutes()*-1);
        }
    }


}


}
