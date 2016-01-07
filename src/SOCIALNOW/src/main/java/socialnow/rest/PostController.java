package socialnow.rest;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.dao.CommentDao;
import socialnow.dao.PostDao;
import socialnow.dao.UserDao;
import socialnow.forms.Event.Add_Post_Event_Form;
import socialnow.forms.Post.Add_Comment_Form;
import socialnow.forms.Post.Post_Form;
import socialnow.forms.User.User_Token_Form;
import socialnow.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erdem on 11/23/2015.
 * post related requests are here
 */

@RestController
public class PostController {
    @Autowired
    private PostDao postDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommentDao  commentDao ;


    final Gson gson = new Gson();

    /**
     * Creates a post in database
     *
     *
     * @param createPostForm
     * @return
     */
    @RequestMapping( value = "/createPost", method = RequestMethod.POST)
    public @ResponseBody
    Post createPost(@RequestBody String createPostForm){
        Post_Form form = gson.fromJson(createPostForm, Post_Form.class);
        Post p = new Post(form);
        postDao.create(p);
        return p;
    }

    /**
     * my posts as a user
     *
     * @param token
     * @return
     */
    @RequestMapping( value = "/listPostsOfUser", method = RequestMethod.POST)
    public @ResponseBody
    List<Post> listPostsOfUser(@RequestBody String token){
        User_Token_Form form = gson.fromJson(token, User_Token_Form.class);
      return postDao.getAllByToken(form.getUser_token());
    }

    /**
     * all the posts within a group
     *
     * @param token
     * @return
     */
    @RequestMapping( value = "/listPostsOfGroup", method = RequestMethod.POST)
    public @ResponseBody
    List<Post> listPostsOfGroup(@RequestBody String token){

        User_Token_Form form = gson.fromJson(token, User_Token_Form.class);
        return postDao.getAllByToken(form.getUser_token());
    }

    /**
     * Details of a post including the owner and comments
     *
     * @param id
     * @return
     */
    @RequestMapping( value = "/getPostDetail", method = RequestMethod.POST)
    public @ResponseBody
    PostDetail getPostDetail(@RequestBody String id){
        Add_Post_Event_Form form = gson.fromJson(id, Add_Post_Event_Form.class);
        Post post = postDao.getById(form.getPost_id());
        PostDetail postDetail = new PostDetail(post);
        postDetail.setOwner(userDao.getByToken(post.getOwner_token()));
        return  postDetail;
    }


    /**
     * adds a comment to  a post with the necessary keys
     *
     * @param addCommentForm
     * @return
     */
    @RequestMapping( value = "/post/addComment", method = RequestMethod.POST)
    public @ResponseBody
    PostDetail addComment(@RequestBody String addCommentForm){
        Add_Comment_Form form = gson.fromJson(addCommentForm, Add_Comment_Form.class);
        Post post = postDao.getById(form.getPost_id());
        post.setPost_comments(post.getPost_comments()+"," + form.getComment_id());
        postDao.update(post);
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

        return  postDetail;
    }

}
