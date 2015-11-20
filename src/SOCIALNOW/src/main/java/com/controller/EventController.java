package com.controller;

/**
 * Created by Erdem on 11/20/2015.
 */


import com.database.UserConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class EventController {
    Gson gson = new GsonBuilder()
            .setDateFormat("dd/MM/yyyy")
            .create();
    @RequestMapping( value = "/addEvent", method = RequestMethod.POST)
    public @ResponseBody
    User addEvent(@RequestBody String addEventForm) {
        User user = new User();
        AddEventForm form = gson.fromJson(addEventForm, AddEventForm.class);
        if(UserConnection.addEvent(Integer.parseInt(form.getUser_id()),form.getTitle(),form.getDescription(),form.getDate())){
        user.setId(form.getUser_id());
            return user;
        }else{
            ErrorJSON error = new ErrorJSON();
            error.setMessage("Database Error");
            error.setCode(8081);
            user.setErrorJSON(error);
            return user;

        }

    }
}
