package com.socialnow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.Models.Event;
import com.socialnow.Models.Group;
import com.socialnow.Models.Post;
import com.socialnow.Users.Utils;


/**
 * Created by lauamy on 20/11/15.
 */
public class CommentActivity extends AppCompatActivity{
    String mTitle = "Edit Comment";
    EditText etComment;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        etComment = (EditText) findViewById(R.id.etComment);
        String callingFlag = getIntent().getStringExtra("flag");
        switch (callingFlag) {
            case "addComment":
                mTitle = "Add Comment";
                id = getIntent().getLongExtra("group_id", -1);
                break;

            case "addReply":
                mTitle = "Add Reply";
                etComment.setHint("Your Reply");
                break;

            default:
                etComment.setText("Comment to be edited.");
                break;
        }

        getSupportActionBar().setTitle(mTitle);
    }

    public void save(View v){
        if(mTitle.equals("Add Comment")){
            Log.d("comment","comment");
            Post p = new Post();
            p.setContent(etComment.getText().toString());
            p.setOwner_token(Utils.getCurrentUser().getUser_token());




            Response.Listener<Post> response = new Response.Listener<Post>() {
                @Override
                public void onResponse(Post response) {
                    if(response.getId() != -1) {
                        Log.d("Post", "Creating success " + response.getId());
                        Response.Listener<Group> response2 = new Response.Listener<Group>() {
                            @Override
                            public void onResponse(Group response) {
                                if(response.getId() != -1) {
                                    Log.d("addpost", "Creating success " + response.getGroup_description());

                                }else{
                                    Log.d("addpost", "Error: Unknown");
                                }
                            }
                        };

                        Response.ErrorListener errorListener = new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Failed", "Add post Failed");

                            }
                        };
                        String body = "{interest_group_id:" + id + ", post_id:" + response.getId() + "}";
                        API.addPost("createEvent", body, response2, errorListener);
                    }else{
                        Log.d("Post", "Error: Unknown");
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Failed", "Creation Failed");

                }
            };
            API.createPost("createPost", p, response, errorListener);
        }
    }
}