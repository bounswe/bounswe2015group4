package socialnow.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.Utils.Error_JSON;
import socialnow.Utils.Util;
import socialnow.dao.EventDao;
import socialnow.dao.Interest_GroupDao;
import socialnow.dao.PostDao;
import socialnow.dao.UserDao;
import socialnow.forms.Interest_Group.Add_Member_Form;
import socialnow.forms.Interest_Group.Add_Post_Form;
import socialnow.forms.Interest_Group.Interest_Group_Form;
import socialnow.model.*;

import java.util.ArrayList;
import java.util.Arrays;

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
    @Autowired
    private PostDao postDao;
    Gson gson = new GsonBuilder()
            .setDateFormat("dd/mm/yyyy")
            .create();
    @RequestMapping( value = "/createInterestGroup", method = RequestMethod.POST)
    public @ResponseBody
    Interest_Group addGroup(@RequestBody String addGroupForm) {
        Interest_Group_Form form = gson.fromJson(addGroupForm,Interest_Group_Form.class);
        Interest_Group ig = new Interest_Group(form);
        User u = userDao.getByToken(form.getOwner_token());
        u.setUser_tags(u.getUser_tags()+ ig.getTags());
        groupDao.create(ig);
        u.setUser_interest_groups(u.getUser_interest_groups()+","+ig.getId());
        userDao.update(u);
        return  ig;
    }


    @RequestMapping( value = "/groups/addPost", method = RequestMethod.POST)
    public @ResponseBody
    Interest_Group addPost(@RequestBody String addPostForm) {
        Add_Post_Form form = gson.fromJson(addPostForm,Add_Post_Form.class);
         Post p = postDao.getById(form.getPost_id());
        Interest_Group group = groupDao.getById(form.getInterest_group_id());
        group.setGroup_posts(group.getGroup_posts()+","+p.getId());
        groupDao.update(group);
        return  group;
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
            errorJSON.setMessage("This user is not  a member of this group ");
            return new Interest_Group(errorJSON);
        }
        u.setUser_interest_groups(Util.deleteFromArray(u.getUser_interest_groups().split(","),ig.getId()+""));
        u.setUser_tags(Util.deleteFromArray(u.getUser_tags().split(","),ig.getTags().split(",")));
        ig.setGroup_members(Util.deleteFromArray(ig.getGroup_members().split(","),u.getUser_token()));
        userDao.update(u);
        groupDao.update(ig);
        return  ig;
    }

    @RequestMapping( value = "/groups/showGroupDetail", method = RequestMethod.POST)
    public @ResponseBody
    Interest_Group_Detail showGroupDetail(@RequestBody String addMemberForm) {
        Add_Member_Form form = gson.fromJson(addMemberForm,Add_Member_Form.class);
        Interest_Group group = groupDao.getById(form.getId());
        Interest_Group_Detail detail = new Interest_Group_Detail(group);
        User u = userDao.getByToken(group.getOwner_token());
        detail.setOwner(u);                         //Owner user detail is ready to be sent
        String tag = group.getTags();
        if(tag.contains(",")) {                     // Tags are ready to be sent
            ArrayList<String > tags= new ArrayList<String>(Arrays.asList(tag.substring(1).split(",")));
            detail.setTags(tags);
        }
        String group_members = group.getGroup_members();
        ArrayList<User> group_member_users = new ArrayList<>();
        if(group_members.contains(",")) {          // members list is ready to go
            ArrayList<String > members= new ArrayList<String>(Arrays.asList(group_members.substring(1).split(",")));
            for (String member: members ) {
                User usr = userDao.getByToken(member);
                group_member_users.add(usr);
            }
        }
        detail.setGroup_members(group_member_users);


        String group_posts = group.getGroup_posts();
        ArrayList<PostDetail> posts = new ArrayList<>();
        if(group_posts.contains(",")) {          // post list is ready to go
            String[] arr= group_posts.split(",");
            for (int i = 0; i <arr.length ; i++) {
                if(!arr[i].equals("")){
                    Post p = postDao.getById(arr[i]);
                    PostDetail postDetail = new PostDetail(p);
                    postDetail.setOwner(userDao.getByToken(p.getOwner_token()));
                    posts.add(postDetail);
                }
            }
        }
        detail.setGroup_posts(posts);
        return  detail;
    }









}
