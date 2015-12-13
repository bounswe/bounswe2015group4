package socialnow.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialnow.dao.CommentDao;
import socialnow.dao.EventDao;
import socialnow.dao.PostDao;
import socialnow.dao.UserDao;
import socialnow.forms.Comment.Create_Comment_Form;
import socialnow.model.Comment;
import socialnow.model.User;

import java.util.logging.Logger;

/**
 * Created by Erdem on 12/13/2015.
 *
 *
 */
@RestController
public class CommentController {
    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private CommentDao commentDao;
    Logger log = Logger.getLogger("COMMENT_CONTROLLER");
    // Creates the json object which will manage the information received
    Gson gson = new GsonBuilder()
            .setDateFormat("dd/MM/yyyy hh:mm")
            .create();
    @RequestMapping( value = "/createComment", method = RequestMethod.POST)
    public @ResponseBody
    Comment createPost(@RequestBody String createCommentForm){
        Create_Comment_Form form = gson.fromJson(createCommentForm, Create_Comment_Form.class);
        Comment c = new Comment(form);
        commentDao.create(c);
        return c;
    }







}
