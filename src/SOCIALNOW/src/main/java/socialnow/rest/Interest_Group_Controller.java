package socialnow.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.Utils.Error_JSON;
import socialnow.Utils.Util;
import socialnow.dao.*;
import socialnow.forms.Interest_Group.Add_Member_Form;
import socialnow.forms.Interest_Group.Add_Post_Form;
import socialnow.forms.Interest_Group.Interest_Group_Form;
import socialnow.forms.User.User_Token_Form;
import socialnow.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Erdem on 12/4/2015.
 *
 * Interest group related requests are here
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
    @Autowired
    private CommentDao commentDao;
    Gson gson = new GsonBuilder()
            .setDateFormat("dd/mm/yyyy")
            .create();

    /**
     * Creates an interest group object and puts it into database, request sends the parameters
     * @param addGroupForm
     * @return
     */
    @RequestMapping( value = "/createInterestGroup", method = RequestMethod.POST)
    public @ResponseBody
    Interest_Group addGroup(@RequestBody String addGroupForm) {
        Interest_Group_Form form = gson.fromJson(addGroupForm,Interest_Group_Form.class);
        Interest_Group ig = new Interest_Group(form);
        User u = userDao.getByToken(form.getOwner_token());
        u.setUser_tags(u.getUser_tags()+ ig.getTags());
        ig.setGroup_members(ig.getGroup_members()+","+ u.getUser_token());
        groupDao.create(ig);
        u.setUser_interest_groups(u.getUser_interest_groups()+","+ig.getId());
        userDao.update(u);
        return  ig;
    }

    /**
     * adds a created post to a group, updates group posts
     * @param addPostForm
     * @return
     */
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


    /**
     * adds a user to a group, updates both group and user
     * @param addMemberForm
     * @return
     */
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
    /**
     * removes  a user from a group, updates both group and user
     * @param addMemberForm
     * @return
     */
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
    /**
     * returns the extended version of Interest_Group,
     * opens up all other sub objects and gives their details as well
     * @param addMemberForm
     * @return
     */
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
                    Post post = postDao.getById(arr[i]);
                    String comments = post.getPost_comments();
                    List<Comment_Details> COMMENT_DETAILS_LIST = new ArrayList<>();
                    if(comments.contains(",")){
                        comments = comments.substring(1);
                        String[] commentArray = comments.split(",");

                        for (int j = 0; j <commentArray.length ; j++) {
                            String  commentId = commentArray[j];
                            Comment cm = commentDao.getById(commentId);
                            Comment_Details cm_details = new Comment_Details(cm);
                            cm_details.setOwner(userDao.getByToken(cm.getOwner_token()));
                            COMMENT_DETAILS_LIST.add(cm_details);
                        }
                    }
                    PostDetail postDetail = new PostDetail(post);
                    postDetail.setOwner(userDao.getByToken(post.getOwner_token()));
                    postDetail.setComments(COMMENT_DETAILS_LIST);
                    posts.add(postDetail);
                }
            }
        }
        Collections.reverse(posts);
        detail.setGroup_posts(posts);
        return  detail;
    }

    @RequestMapping( value = "/listAllGroups", method = RequestMethod.POST)
    public @ResponseBody
    List<Interest_Group> listAllGroups(@RequestBody String userTokenForm) {
        User_Token_Form form = gson.fromJson(userTokenForm,User_Token_Form.class);
        User u = userDao.getByToken(form.getUser_token());
        return groupDao.getAllForUser(u);
    }

    @RequestMapping( value = "/listMyGroups", method = RequestMethod.POST)
    public @ResponseBody
    List<Interest_Group> ListMyGroups(@RequestBody String addPostForm) {
        User_Token_Form form = gson.fromJson(addPostForm, User_Token_Form.class);
        return groupDao.getAllByToken(form.getUser_token());
    }

    @RequestMapping( value = "/listParticipatedGroups", method = RequestMethod.POST)
    public @ResponseBody
    List<Interest_Group> ListParticipatedGroups(@RequestBody String addPostForm) {
        User_Token_Form form = gson.fromJson(addPostForm, User_Token_Form.class);
        List<Interest_Group> participatedGroups = new ArrayList<>();
        List<Interest_Group> allgroups =  groupDao.getAll();
        for (Interest_Group g: allgroups) {
                if (g.getGroup_members().contains(form.getUser_token()))
                    participatedGroups.add(g);
        }
        return participatedGroups;
    }

}
