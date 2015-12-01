package com.socialnow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Parcelable;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.parse.FindCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.socialnow.API.API;
import com.socialnow.Models.Event;
import com.socialnow.Models.User;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lauamy on 27/10/15.
 */
public class EventFrag extends Fragment {
    private ListView listView;
    private View v;

    int maxCount = 0;
    List<Event> events;
    List<String> titles;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        v = inflater.inflate(R.layout.frag_event, container, false);
        listView = (ListView)v.findViewById(R.id.lvEvent);
        events = new LinkedList<>();
        titles = new LinkedList<>();
        getData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listView.getItemAtPosition(position);
                Event event = events.get(position);
                Intent i2 = new Intent(getActivity(), EventActivity.class);
                //i2.putExtra("Event", event);
                i2.putExtra("title", event.getTitle());
                i2.putExtra("date", event.getEvent_date());
                i2.putExtra("location", event.getEvent_location());
                i2.putExtra("hostname", event.getEvent_host_token());
                i2.putExtra("participants", event.getEvent_participants());
                i2.putExtra("photo", event.getEvent_photo());
                i2.putExtra("description", event.getEvent_description());
                startActivity(i2);
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
            img.setImageBitmap(events.get(position).getEvent_photo());
            TextView eventdate = (TextView) v.findViewById(R.id.tEdate);
            eventdate.setText(ft.format(new Date(events.get(position).getEvent_date())));
            TextView eventlocation = (TextView) v.findViewById(R.id.tElocation);
            eventlocation.setText(events.get(position).getEvent_location());
            TextView eventhost = (TextView) v.findViewById(R.id.tHostName);
            eventhost.setText(events.get(position).getEvent_host_token());


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
}

