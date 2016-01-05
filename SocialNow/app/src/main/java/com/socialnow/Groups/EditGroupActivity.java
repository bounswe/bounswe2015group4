package com.socialnow.Groups;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.apradanas.simplelinkabletext.Link;
import com.socialnow.API.API;
import com.socialnow.Models.Event;
import com.socialnow.Models.Group;
import com.socialnow.PartiActivity;
import com.socialnow.R;
import com.socialnow.Users.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by lauamy on 20/11/15.
 */
public class EditGroupActivity extends AppCompatActivity{
    String mTitle="Create Group";
    Spinner spinner;

    TextView etGroupName, etGroupDes,etPhoto;
    com.apradanas.simplelinkabletext.LinkableEditText tvTags;
    ArrayAdapter<CharSequence> adapter;
    String privacy_option = "";
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editgroup);
        /*Intent i = getIntent();

        Boolean editFlag = false;
        if(i != null && i.getStringExtra("type").equals("create")){
            editFlag = false;
        }else
            editFlag = true;


        if (editFlag){
            mTitle = "Edit Event";
        }*/
        context = this;

        getSupportActionBar().setTitle(mTitle);

        etGroupName = (TextView) findViewById(R.id.etGroupName);
        etGroupDes = (TextView) findViewById(R.id.etGroupDes);
        tvTags = (com.apradanas.simplelinkabletext.LinkableEditText) findViewById(R.id.tags);

        etPhoto = (EditText) findViewById(R.id.etPhoto);
        spinner = (Spinner) findViewById(R.id.sPrivacy);
        adapter = ArrayAdapter.createFromResource(this, R.array.group_privacy, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.Member);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addMember = new Intent(getApplicationContext(), PartiActivity.class).putExtra("from", "EditGroupActivity");
                startActivity(addMember);
                //TODO activity should be started to get results as the guests to be invited.
            }
        });

        Link linkHashtag = new Link(Pattern.compile("(\\w+)"))
                .setUnderlined(true)
                .setTextStyle(Link.TextStyle.BOLD)
                .setClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String text) {
                        Toast.makeText(EditGroupActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                });
        List<Link> links = new ArrayList<>();
        links.add(linkHashtag);

        tvTags.addLinks(links);


    }

    public void create_group(View v){
        if(inputs_correct()){
            Group group = new Group();
            String event_tags = tvTags.getText().toString();
            String tagArray[] = event_tags.split("\\s+");
            String tagForDb = "";
            for(int i = 0;i<tagArray.length;i++){
                tagForDb += tagArray[i] + ",";
            }
            group.setGroup_tags(tagForDb);
            group.setGroup_name(etGroupName.getText().toString());
            group.setOwner_token(Utils.getCurrentUser().getUser_token());
            group.setGroup_description(etGroupDes.getText().toString());
            group.setGroup_photo(etPhoto.getText().toString());
            //TODO change the visibleTo value when spinner done
            group.set_visibleTo("all");
            //TODO guest member adding should be handled
            //event.put("event_members", )


            Response.Listener<Group> response = new Response.Listener<Group>() {
                @Override
                public void onResponse(Group response) {
                    if(response.getId() != -1) {
                        Log.d("Group", "Creating success " + response.getGroup_description());
                        finish();
                    }else{
                        Log.d("Creation", "Error: Unknown");
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Failed", "Creation Failed");

                }
            };
            API.createInterestGroup("createInterestGroup", group, response, errorListener);

            Toast.makeText(getBaseContext(), "Doing", Toast.LENGTH_LONG).show();


        }else
        {
            Toast.makeText(getBaseContext(), "Please make sure you give a group name and a description", Toast.LENGTH_LONG).show();
        }
    }

    private boolean inputs_correct() {
        if(etGroupName.getText().toString() == "" || etGroupDes.getText().toString() == "")
        {
            return false;

        }
        return true;
    }
}
