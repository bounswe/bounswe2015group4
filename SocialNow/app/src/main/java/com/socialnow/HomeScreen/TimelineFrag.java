package com.socialnow.HomeScreen;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.Models.Basicgroup_event;
import com.socialnow.Models.Event;
import com.socialnow.Models.Group;
import com.socialnow.Models.TimelineReturn;
import com.socialnow.Models.User;
import com.socialnow.Models.User_Event;
import com.socialnow.Models.User_Group;
import com.socialnow.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TimelineFrag extends Fragment {
    TimelineReturn timeLine;
    ListView listView;
    ArrayList<Basicgroup_event> results = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timeline, container, false);
        listView = (ListView)v.findViewById(R.id.timeLine);
        getData();
        return v;
    }


    public void getData() {
        Response.Listener<TimelineReturn> response = new Response.Listener<TimelineReturn>() {
            @Override
            public void onResponse(TimelineReturn response) {
                if(response != null) {
                    Log.d("Timeline", "success" + response.getUser_events().size() + " " + response.getUser_groups().size());
                    timeLine = response;
                    handleTimeline();
                    listView.setAdapter(new MyAdapter(getActivity(), R.layout.item_timeline, results));
                }else{
                    Log.d("Timeline", "error");
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", error.toString());

            }
        };

        API.getTimeLine("timeline", response, errorListener);
    }

    private void handleTimeline() {
        List<User_Event> ue = timeLine.getUser_events();
        List<User_Group> ug = timeLine.getUser_groups();
        for(int i = 0; i < ue.size(); i++){
            User_Event t = ue.get(i);
            results.add(new Basicgroup_event(t.getUser().getUser_photo(), t.getUser().getName() + " " + t.getUser().getSurname(), t.getEvent().getTitle()));
        }
        for(int i = 0; i < ug.size(); i++){
            User_Group t = ug.get(i);
            results.add(new Basicgroup_event(t.getUser().getUser_photo(), t.getUser().getName() + " " + t.getUser().getSurname(), t.getGroup().getGroup_name()));
        }
    }

    class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_event,null);
            TextView info = (TextView) v.findViewById(R.id.tInfo);
            info.setText(results.get(position).owner + " created " + results.get(position).name);
            ImageView img = (ImageView) v.findViewById(R.id.ivEvent);
//            Log.d("events", events.get(position).getEvent_photo() + " " + events.get(position).getEvent_description() + " " + events.get(position).getEvent_participant_users().length);

            if(results.get(position).photoUrl == null){
                img.setImageResource(R.drawable.devent);
            }else{
                Picasso.with(((Activity) getContext()))
                        .load(results.get(position).photoUrl)
                        .resize(80, 80)
                        .placeholder(R.drawable.devent)
                        .centerCrop()
                        .into(img);
            }


            return v;
        }
    }
}
