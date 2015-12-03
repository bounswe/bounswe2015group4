package socialnow.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.Utils.Error_JSON;
import socialnow.Utils.Util;
import socialnow.dao.EventDao;
import socialnow.dao.Interest_GroupDao;
import socialnow.dao.UserDao;
import socialnow.forms.Interest_Group.Add_Member_Form;
import socialnow.forms.Interest_Group.Interest_Group_Form;
import socialnow.model.Interest_Group;
import socialnow.model.User;

/**
 * Created by Erdem on 12/4/2015.
 */

@RestController
public class Interest_Group_Controller {

    @Autowired
    private Interest_GroupDao groupDao;
    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;
    Gson gson = new GsonBuilder()
            .setDateFormat("dd/mm/yyyy")
            .create();
    @RequestMapping( value = "/createInterestGroup", method = RequestMethod.POST)
    public @ResponseBody
    Interest_Group addGroup(@RequestBody String addGroupForm) {
        Interest_Group_Form form = gson.fromJson(addGroupForm,Interest_Group_Form.class);
        Interest_Group ig = new Interest_Group(form);
        User u = userDao.getByToken(form.getOwner_token());
        u.setUser_tags(u.getUser_tags()+","+ ig.getTags());
        groupDao.create(ig);
        userDao.update(u);
        return  ig;
    }

    @RequestMapping( value = "/groups/addMember", method = RequestMethod.POST)
    public @ResponseBody
    Interest_Group addMember(@RequestBody String addMemberForm) {
        Add_Member_Form form = gson.fromJson(addMemberForm,Add_Member_Form.class);
        Interest_Group ig = groupDao.getById(form.getId());
        User u = userDao.getByToken(form.getUser_token());
        if(Util.arrayContains(u.getUser_interest_groups().split(","), ig.getId()+"")){
            Error_JSON errorJSON = new Error_JSON();
            errorJSON.setCode(8082);
            errorJSON.setMessage("This user is already a member of this group ");
            return new Interest_Group(errorJSON);
        }
        ig.setGroup_members(ig.getGroup_members()+","+u.getUser_token());
        u.setUser_interest_groups(u.getUser_interest_groups()+","+ ig.getId());
        u.setUser_tags(u.getUser_tags() + ig.getTags());
        userDao.update(u);
        groupDao.update(ig);
        return  ig;
    }
    @RequestMapping( value = "/groups/removeMember", method = RequestMethod.POST)
    public @ResponseBody
    Interest_Group removeMember(@RequestBody String addMemberForm) {
        Add_Member_Form form = gson.fromJson(addMemberForm,Add_Member_Form.class);
        Interest_Group ig = groupDao.getById(form.getId());
        User u = userDao.getByToken(form.getUser_token());
        if(!Util.arrayContains(u.getUser_interest_groups().split(","), ig.getId()+"")){
            Error_JSON errorJSON = new Error_JSON();
            errorJSON.setCode(8082);
            errorJSON.setMessage("This user is not  a member of this event ");
            return new Interest_Group(errorJSON);
        }
        u.setUser_interest_groups(Util.deleteFromArray(u.getUser_interest_groups().split(","),ig.getId()+""));
        u.setUser_tags(Util.deleteFromArray(u.getUser_tags().split(","),ig.getTags().split(",")));
        ig.setGroup_members(Util.deleteFromArray(ig.getGroup_members().split(","),u.getUser_token()));
        userDao.update(u);
        groupDao.update(ig);
        return  ig;
    }



}
