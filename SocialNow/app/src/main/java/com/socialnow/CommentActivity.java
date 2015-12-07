package com.socialnow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;


/**
 * Created by lauamy on 20/11/15.
 */
public class CommentActivity extends AppCompatActivity{
    String mTitle = "Edit Comment";
    EditText etComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        etComment = (EditText) findViewById(R.id.etComment);
        String callingFlag = getIntent().getStringExtra("flag");
        switch (callingFlag) {
            case "addComment":
                mTitle = "Add Comment";
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
}