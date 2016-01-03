package com.socialnow.Groups;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.Models.Event;
import com.socialnow.Models.Group;
import com.socialnow.PartiActivity;
import com.socialnow.R;
import com.socialnow.Users.Utils;

import java.util.Calendar;

/**
 * Created by lauamy on 20/11/15.
 */
public class EditGroupActivity extends AppCompatActivity{
    String mTitle="Create Group";
    Spinner spinner;

    TextView etGroupName, etGroupDes, tvTags;
    ArrayAdapter<CharSequence> adapter;
    String privacy_option = "";
    Context context;
    String gTitle1 = "Invalid Date";
    String gTitle2 = "Invalid Time";
    String gTitle3 = "Invalid Date/Time";
    String gMsg1 = "Past date is selected";
    String gMsg2 = "End time is not later than Start Time";
    String gMsg3 = "Past time/date cannot be inputted";

    final Calendar c = Calendar.getInstance();
    int mCHour = c.get(Calendar.HOUR_OF_DAY);
    int mCMinute = c.get(Calendar.MINUTE);
    int CurrentTime = mCHour*60+mCMinute;

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
        tvTags = (TextView) findViewById(R.id.tags);


        // etEventName.setText("first");
        // etEventDes.setText("desc");
        // etEventLoca.setText("loca");
        /*if (!editFlag){
            tvDate.setText("2015-11-3");
            tvSTime.setText("20:45");
            tvETime.setText("21:45");
        }*/

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


    }

    public void create_group(View v){
        if(inputs_correct()){
            Group group = new Group();
            group.setGroup_tags(tvTags.getText().toString());
            group.setGroup_name(etGroupName.getText().toString());
            group.setGroup_owner_token(Utils.getCurrentUser().getUser_token());
            group.setGroup_description(etGroupDes.getText().toString());
//            event.setEvent_photo(getEventPhoto());
            group.setGroup_photo("");
            group.set_visibleTo("all");
            //TODO guest member adding should be handled
            //event.put("event_members", )
            //TODO comment part should also be handled

            //event.put("event_comments", )

            Response.Listener<Group> response = new Response.Listener<Group>() {
                @Override
                public void onResponse(Group response) {
                    if(response.getId() != -1) {
                        Log.d("Group", "Creating success " + response.getGroup_description());

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
            API.createGroup("createInterestGroup", group, response, errorListener);

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
