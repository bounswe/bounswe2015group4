package socialnow.rest;

import com.google.gson.Gson;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.Utils.Error_JSON;
import socialnow.Utils.Util;
import socialnow.dao.*;
import socialnow.forms.Notification.Notification_Form;
import socialnow.forms.User.Login_Form;
import socialnow.forms.User.User_Token_Form;
import socialnow.model.Interest_Group;
import socialnow.model.Notification;
import socialnow.model.Notification_Detail;
import socialnow.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Erdem on 12/11/2015.
 * Sharing related requests are here now
 */

@RestController
public class ShareController {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private Interest_GroupDao groupDao;
    @Autowired
    private NotificationDao notificationDao;

    final Gson gson = new Gson();
    Logger log = Logger.getLogger("EVENTCONTROLLER");

    @RequestMapping( value = "/users/shareEvent", method = RequestMethod.POST)
    public @ResponseBody
    Notification shareWithUser(@RequestBody String notification_form_data) {
        Notification_Form form = gson.fromJson(notification_form_data, Notification_Form.class);
        if(Util.canSeeEvent(userDao.getByToken(form.getTo_user_token()), eventDao.getById(form.getEvent_id())))
        {
            Notification notf = new Notification(form);
            notificationDao.create(notf);
            return  notf;
        }
        return new Notification();
    }

    @RequestMapping( value = "/removeNotification", method = RequestMethod.POST)
    public @ResponseBody
    User removeNotification(@RequestBody String user_token) {
        User_Token_Form form = gson.fromJson(user_token, User_Token_Form.class);
        notificationDao.removeToUser(form.getUser_token());
        return  userDao.getByToken(form.getUser_token());
    }

    @RequestMapping( value = "/groups/shareEvent", method = RequestMethod.POST)
    public @ResponseBody
    List<Notification> shareWithGroup(@RequestBody String notification_form_data) {
        List<Notification> notfs = new ArrayList<>();
        Notification_Form form = gson.fromJson(notification_form_data, Notification_Form.class);
        Interest_Group group = groupDao.getById(form.getGroup_id());
        String members = group.getGroup_members();
        members = members.substring(1);
        String[] memberTokens = members.split(",");
        for (int i = 0; i <memberTokens.length ; i++) {
            String memberToken = memberTokens[i];
            if(!Util.canSeeEvent(userDao.getByToken(memberToken),eventDao.getById(form.getEvent_id()))) {
                Notification notf = new Notification();
                notf.setEvent(form.getEvent_id());
                notf.setFrom_user(form.getFrom_user_token());
                notf.setTo_user(memberToken);
                notificationDao.create(notf);
                notfs.add(notf);
            }
        }
        return  notfs;
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
            detail.setFrom_user(userDao.getByToken(notf.getFrom_user()));
            detail.setTo_user(userDao.getByToken(notf.getTo_user()));

            notfDetailList.add(detail);
        }
        return  notfDetailList;
    }

}
