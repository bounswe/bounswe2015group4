package com.socialnow.HomeScreen;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.Events.EditEventActivity;
import com.socialnow.Events.EventActivity;
import com.socialnow.Models.Event;
import com.socialnow.Models.Instant_Event_Details;
import com.socialnow.Models.User;
import com.socialnow.R;
import com.socialnow.Util.EditInstantEvent;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mugekurtipek on 06/01/16.
 */
public class InstantEventFrag extends Fragment {
    private ListView listView;
    private View v;
    List<Instant_Event_Details> instantEvents;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.frag_event, container, false);
        listView = (ListView) v.findViewById(R.id.lvEvent);
        instantEvents = new LinkedList<>();
        TextView tvUpEvents = (TextView) v.findViewById(R.id.tvUpEvents);
        tvUpEvents.setText("");

        getData();


        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getActivity(), EditInstantEvent.class);
                startActivity(i2);
            }
        });

        return v;


    }

    class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
            View v = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_instantevent, null);
            TextView eventname = (TextView) v.findViewById(R.id.tEname);
            eventname.setText(instantEvents.get(position).getTitle());
            ImageView img = (ImageView) v.findViewById(R.id.ivEvent);

            img.setImageResource(R.drawable.ic_people);
           if (instantEvents.get(position).getInstant_event_owner().getUser_photo().equals("")) {
                img.setImageResource(R.drawable.ic_people);
            } else {
                Picasso.with(((Activity) getContext()))
                        .load(instantEvents.get(position).getInstant_event_owner().getUser_photo())
                        .resize(80, 80)
                        .placeholder(R.drawable.devent)
                        .centerCrop()
                        .into(img);
            }


            TextView eventdate = (TextView) v.findViewById(R.id.tEdate);
            eventdate.setText(instantEvents.get(position).getTime_remaining() + " minutes");
            TextView eventlocation = (TextView) v.findViewById(R.id.tElocation);
            eventlocation.setText(instantEvents.get(position).getInstant_event_location());
            TextView tDescription = (TextView) v.findViewById(R.id.tDescription);
            tDescription.setText(instantEvents.get(position).getInstant_event_description());
            TextView tHostName = (TextView) v.findViewById(R.id.tHost);
            Log.d("Name:",instantEvents.get(position).getInstant_event_owner().getName());
            Log.d("Surname:",instantEvents.get(position).getInstant_event_owner().getSurname());
            tHostName.setText("Host by: "+instantEvents.get(position).getInstant_event_owner().getName() + " " + instantEvents.get(position).getInstant_event_owner().getSurname());


            return v;
        }
    }

    public void getData() {
        Response.Listener<Instant_Event_Details[]> response = new Response.Listener<Instant_Event_Details[]>() {
            @Override
            public void onResponse(Instant_Event_Details[] response) {
                if (response != null) {
                    Log.d("InstantEvent", response.toString());
                    for (int i = 0; i < response.length; i++) {
                        instantEvents.add(i, response[i]);
                    }
                    writeData();

                } else {
                    Log.d("InstantEvent", "error");
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", error.toString());

            }
        };

        API.getInstantEvent("getInstantEvent","muge", response, errorListener);
    }


        public void writeData(){
            listView.setAdapter(new MyAdapter(getActivity(), R.layout.item_instantevent, instantEvents));
        }


}
