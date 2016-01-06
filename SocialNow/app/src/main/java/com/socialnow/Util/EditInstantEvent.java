package com.socialnow.Util;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.Models.Event;
import com.socialnow.Models.Instant_Event;
import com.socialnow.Models.Instant_Event_Details;
import com.socialnow.R;
import com.socialnow.Users.Utils;

/**
 * Created by mugekurtipek on 06/01/16.
 */
public class EditInstantEvent extends AppCompatActivity {
    EditText etEventName,etEventDes,etEventLoca,etDuration;
    Button bSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instantevent);
        bSave = (Button) findViewById(R.id.bSave);
        etEventName = (EditText) findViewById(R.id.etEventName);
        etEventDes = (EditText) findViewById(R.id.etEventDes);
        etEventLoca = (EditText) findViewById(R.id.etEventLoca);
        etDuration = (EditText) findViewById(R.id.etDuration);
        getSupportActionBar().setTitle("Create Instant Event");

    }
    public void create_event(View v){
        Instant_Event instantEvent = new Instant_Event();
        instantEvent.setDuration_in_minutes(Integer.parseInt(etDuration.getText().toString()));
        instantEvent.setInstant_event_description(etEventDes.getText().toString());
        instantEvent.setInstant_event_location(etEventLoca.getText().toString());
        instantEvent.setTitle(etEventName.getText().toString());
        instantEvent.setInstant_event_owner(Utils.getCurrentUser().getUser_token());
        Response.Listener<Instant_Event> response = new Response.Listener<Instant_Event>() {
            @Override
            public void onResponse(Instant_Event response) {
                if(response.getId() != -1) {
                    Log.d("Instant_Event_Details", "Creating success " + response.getInstant_event_description());
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
                finish();

            }
        };
        API.createInstantEvent("createInstantEvent", instantEvent, response, errorListener);

        Toast.makeText(getBaseContext(), "Doing", Toast.LENGTH_LONG).show();
    }
}
