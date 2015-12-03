package socialnow.rest;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.dao.PostDao;
import socialnow.dao.UserDao;
import socialnow.forms.Event.Add_Post_Event_Form;
import socialnow.forms.Post.Post_Form;
import socialnow.forms.User.User_Token_Form;
import socialnow.model.Interest_Group;
import socialnow.model.Post;
import socialnow.model.PostDetail;
import socialnow.model.User;

import java.util.List;

/**
 * Created by Erdem on 11/23/2015.
 */

@RestController
public class PostController {
    @Autowired
    private PostDao postDao;
    @Autowired
    private UserDao userDao;
    final Gson gson = new Gson();

    @RequestMapping( value = "/createPost", method = RequestMethod.POST)
    public @ResponseBody
    Post createPost(@RequestBody String createPostForm){
        Post_Form form = gson.fromJson(createPostForm, Post_Form.class);
        Post p = new Post(form);
       User u = userDao.getByToken(p.getOwner_token());
        u.setUser_interest_groups(u.getUser_interest_groups()+","+p.getId());
        postDao.create(p);
        userDao.update(u);
        return p;
    }


    @RequestMapping( value = "/listPostsOfUser", method = RequestMethod.POST)
    public @ResponseBody
    List<Post> listPostsOfUser(@RequestBody String token){
        User_Token_Form form = gson.fromJson(token, User_Token_Form.class);
      return postDao.getAllByToken(form.getUser_token());
    }


    @RequestMapping( value = "/listPostsOfGroup", method = RequestMethod.POST)
    public @ResponseBody
    List<Post> listPostsOfGroup(@RequestBody String token){
        User_Token_Form form = gson.fromJson(token, User_Token_Form.class);
        return postDao.getAllByToken(form.getUser_token());
    }


    @RequestMapping( value = "/getPostDetail", method = RequestMethod.POST)
    public @ResponseBody
    PostDetail getPostDetail(@RequestBody String id){
        Add_Post_Event_Form form = gson.fromJson(id, Add_Post_Event_Form.class);
        Post post = postDao.getById(form.getPost_id());
        PostDetail postDetail = new PostDetail(post);
        postDetail.setOwner(userDao.getByToken(post.getOwner_token()));
        return  postDetail;
    }







}
