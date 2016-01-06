package com.socialnow.HomeScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.Events.EditEventActivity;
import com.socialnow.Events.EventActivity;
import com.socialnow.Models.Event;
import com.socialnow.Models.User;
import com.socialnow.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lauamy on 27/10/15.
 */

public class EventFrag extends Fragment {
    private ListView listView;
    private View v;
    static String hostUserName;
    int maxCount = 0;
    List<Event> events;
    List<String> titles;
    String bundleValue = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        v = inflater.inflate(R.layout.frag_event, container, false);
        listView = (ListView)v.findViewById(R.id.lvEvent);
        events = new LinkedList<>();
        titles = new LinkedList<>();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            bundleValue = bundle.getString("from", "");
            if(bundleValue.equals("HomePage")){
                Log.d("check", "from Home Page");
                getMyData();
            }else if(bundleValue.equals("Search")){
                events = (ArrayList<Event>) bundle.getSerializable("searchEvents");
//                Log.d("events", events.get(0).getEvent_photo() + " " + events.get(0).getEvent_description() + " " + events.get(0).getEvent_participant_users().length);

                writeToList();
                TextView tv = (TextView) v.findViewById(R.id.tvUpEvents);
                String keyword = bundle.getString("keyword", "");
                if(keyword =="")
                    tv.setVisibility(View.INVISIBLE);
                else
                    tv.setText("Results for: " + bundle.getString("keyword", ""));
//                Log.d("eventt", events.size() + " ss");
            }
            else{
                getData();
            }
        }else{
            getData();
        }



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listView.getItemAtPosition(position);
                Event event = events.get(position);

                Intent i2 = new Intent(getActivity(), EventActivity.class);
                i2.putExtra("id", event.getId());
                i2.putExtra("event", event);
                startActivity(i2);
                //i2.putExtra("Event", event);
              /*  i2.putExtra("title", event.getTitle());
                i2.putExtra("date", event.getEvent_date());
                i2.putExtra("location", event.getEvent_location());
                i2.putExtra("hostname", hostUserName);
                //i2.putExtra("participants", event.getEvent_participants());
                i2.putExtra("photo", event.getEvent_photo());
                i2.putExtra("description", event.getEvent_description());
                i2.putExtra("id", event.getId());
                startActivity(i2);*/
            }
        });

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getActivity(), EditEventActivity.class);
                i2.putExtra("type", "create");
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
            View v=((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_event,null);
            TextView eventname = (TextView) v.findViewById(R.id.tEname);
            eventname.setText(events.get(position).getTitle());
            ImageView img = (ImageView) v.findViewById(R.id.ivEvent);
//            Log.d("events", events.get(position).getEvent_photo() + " " + events.get(position).getEvent_description() + " " + events.get(position).getEvent_participant_users().length);

            if(events.get(position).getEvent_photo() == null){
                img.setImageResource(R.drawable.devent);
            }else{
                Picasso.with(((Activity)getContext()))
                        .load(events.get(position).getEvent_photo())
                        .resize(80, 80)
                        .placeholder(R.drawable.devent)
                        .centerCrop()
                        .into(img);
            }

            User[] hostUser = events.get(position).getEvent_participant_users();
            TextView eventhost = (TextView) v.findViewById(R.id.tHostName);
            if(hostUser.length > 0) {
                hostUserName = hostUser[0].getName() + " " + hostUser[0].getSurname();
                eventhost.setText(hostUserName);
            }else {
                hostUserName = "";
                eventhost.setVisibility(View.INVISIBLE);
                TextView eventhostt = (TextView) v.findViewById(R.id.tHost);
                eventhostt.setVisibility(View.INVISIBLE);
            }
            //TODO date is not in correct form
            TextView eventdate = (TextView) v.findViewById(R.id.tEdate);
            eventdate.setText(events.get(position).getEvent_date_as_date());
            TextView eventlocation = (TextView) v.findViewById(R.id.tElocation);
            eventlocation.setText(events.get(position).getEvent_location());


            return v;
        }
    }

    void getData() {

        Response.Listener<Event[]> response = new Response.Listener<Event[]>() {
            @Override
            public void onResponse(Event[] response) {
                if(response != null) {
                    Log.d("Event", response.toString());
                    for( int i= 0;i<response.length;i++){
                        events.add(i,response[i]);
                    }
                    writeToList();

                }else{
                    Log.d("Event", "error");
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", error.toString());

            }
        };

        API.listAllEvents("listAllEvents", response, errorListener);
    }

    void writeToList(){
        Log.d("Event", events.toString());
        listView.setAdapter(new MyAdapter(getActivity(), R.layout.item_event, events));
    }

    void getMyData() {

        Response.Listener<Event[]> response = new Response.Listener<Event[]>() {
            @Override
            public void onResponse(Event[] response) {
                if(response != null) {
                    Log.d("Event", response.toString());
                    for( int i= 0;i<response.length;i++){
                        events.add(i,response[i]);
                    }
                    writeToList();

                }else{
                    Log.d("Event", "error");
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", error.toString());

            }
        };

        API.listAttendingEvents("listAttendingEvents", response, errorListener);
    }


}


