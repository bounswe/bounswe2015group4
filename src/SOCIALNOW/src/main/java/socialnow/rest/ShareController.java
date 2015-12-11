package socialnow.rest;

import com.google.gson.Gson;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.Utils.Error_JSON;
import socialnow.Utils.Util;
import socialnow.dao.EventDao;
import socialnow.dao.NotificationDao;
import socialnow.dao.PostDao;
import socialnow.dao.UserDao;
import socialnow.forms.Notification.Notification_Form;
import socialnow.forms.User.Login_Form;
import socialnow.forms.User.User_Token_Form;
import socialnow.model.Notification;
import socialnow.model.Notification_Detail;
import socialnow.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Erdem on 12/11/2015.
 */

@RestController
public class ShareController {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;
    @Autowired
    private NotificationDao notificationDao;

    final Gson gson = new Gson();
    Logger log = Logger.getLogger("EVENTCONTROLLER");


    @RequestMapping( value = "/users/shareEvent", method = RequestMethod.POST)
    public @ResponseBody
    Notification shareWithUser(@RequestBody String notification_form_data) {
        Notification_Form form = gson.fromJson(notification_form_data, Notification_Form.class);
        Notification notf = new Notification(form);
        notificationDao.create(notf);
        return  notf;
    }

    @RequestMapping( value = "/users/getNotifications", method = RequestMethod.POST)
    public @ResponseBody
    List<Notification_Detail> getNotification(@RequestBody String userToken) {
        List<Notification_Detail> notfDetailList = new ArrayList<>();
        User_Token_Form form = gson.fromJson(userToken, User_Token_Form.class);
        List<Notification> notfs = notificationDao.getAllByToken(form.getUser_token());
        for (Notification notf: notfs) {
            Notification_Detail detail = new Notification_Detail();
            detail.setEvent(eventDao.getById(notf.getEvent()));
            detail.setFrom_user(userDao.getByToken(notf.getFromUser()));
            detail.setTo_user(userDao.getByToken(notf.getToUser()));

            notfDetailList.add(detail);
        }
        return  notfDetailList;
    }






}
