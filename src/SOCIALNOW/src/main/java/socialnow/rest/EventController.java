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
}
