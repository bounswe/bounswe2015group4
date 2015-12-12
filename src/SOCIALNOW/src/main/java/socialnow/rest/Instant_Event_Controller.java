package socialnow.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.dao.*;
import socialnow.forms.Event.Instant_Event_Form;
import socialnow.model.Instant_Event;

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

    @RequestMapping( value = "/createInstantEvent", method = RequestMethod.POST)
    public @ResponseBody
    Instant_Event addEvent(@RequestBody String addEventForm) {
        Instant_Event_Form form = gson.fromJson(addEventForm, Instant_Event_Form.class);
        Instant_Event e = new Instant_Event(form);
        instant_eventDao.create(e);
        return e;
    }










}
