package socialnow.rest;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.dao.PostDao;
import socialnow.forms.Post_Form;
import socialnow.forms.User_Token_Form;
import socialnow.model.Post;

import java.util.List;

/**
 * Created by Erdem on 11/23/2015.
 */

@RestController
public class PostController {
    @Autowired
    private PostDao postDao;

    final Gson gson = new Gson();

    @RequestMapping( value = "/createPost", method = RequestMethod.POST)
    public @ResponseBody
    Post createPost(@RequestBody String createPostForm){
        Post_Form form = gson.fromJson(createPostForm, Post_Form.class);
        Post p = new Post(form);
        postDao.create(p);
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






}
