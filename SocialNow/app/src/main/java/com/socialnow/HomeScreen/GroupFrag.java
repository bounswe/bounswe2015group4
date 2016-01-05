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
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.Groups.EditGroupActivity;
import com.socialnow.Groups.GroupActivity;
import com.socialnow.Models.Group;
import com.socialnow.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by lauamy on 27/10/15.
 */
public class GroupFrag extends Fragment {
    private ListView listView;
    private View v;
    List<Group> groups;
    //List<String> title;
    List<String> privacy;
    List<Bitmap> photo;
    List<String> member;
    List<Date> update;
    String [] title = {""};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        groups = new LinkedList<>();

        v = inflater.inflate(R.layout.frag_group, container, false);

        listView = (ListView)v.findViewById(R.id.lvGroup);
        /*title = new LinkedList<String>();
        privacy = new LinkedList<String>();
        photo = new LinkedList<Bitmap>();
        member = new LinkedList<String>();
        update = new LinkedList<Date>();*/
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String bundleValue = bundle.getString("from", "");
            if(bundleValue.equals("HomePage")){
                Log.d("check", "from Home Page");
                getMyData();
            }else{
                getData();
            }
        }else{
            getData();
        }



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listView.getItemAtPosition(position);
                Group group = groups.get(position);
                Intent i2 = new Intent(getActivity(), GroupActivity.class);
                i2.putExtra("id", group.getId());
                startActivity(i2);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fAddGroup);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), EditGroupActivity.class);
                //i.putExtra("type", "create");
                startActivity(i);
            }
        });


        return v;
    }
    void getData() {

        Response.Listener<Group[]> response = new Response.Listener<Group[]>() {
            @Override
            public void onResponse(Group[] response) {
                if(response != null) {
                    Log.d("Group", response.toString());
                    for( int i= 0;i<response.length;i++){
                        groups.add(i,response[i]);
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

        API.listAllGroups("listAllGroups", response, errorListener);
    }
    void getMyData() {

        Response.Listener<Group[]> response = new Response.Listener<Group[]>() {
            @Override
            public void onResponse(Group[] response) {
                if(response != null) {
                    Log.d("Group", response.toString());
                    for( int i= 0;i<response.length;i++){
                        groups.add(i,response[i]);
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
//TODO API will change as to show the groups that the user is a member of
        API.listParticipatedGroups("listParticipatedGroups", response, errorListener);
    }


    class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_event,null);
            TextView groupname = (TextView) v.findViewById(R.id.tEname);
            groupname.setText(groups.get(position).getGroup_name());
            ImageView img = (ImageView) v.findViewById(R.id.ivEvent);

            if(!groups.get(position).getGroup_photo().equals("")){
                Picasso.with(((Activity) getContext()))
                        .load(groups.get(position).getGroup_photo())
                        .resize(80, 80)
                        .placeholder(R.drawable.devent)
                        .centerCrop()
                        .into(img);
            }


            //img.setImageResource(R.drawable.ic_people);
            ImageView privacy = (ImageView) v.findViewById(R.id.ivEDate);
            privacy.setImageResource(R.drawable.ic_lock);
            TextView mPrivacy = (TextView) v.findViewById(R.id.tEdate);
            mPrivacy.setText(groups.get(position).get_visibleTo());
            //privacy.setText(date.get(position).toString());
            ImageView member = (ImageView) v.findViewById(R.id.ivELocation);
            member.setImageResource(R.drawable.groupdrawer);
            TextView mMember = (TextView) v.findViewById(R.id.tElocation);
            String s = groups.get(position).getGroup_members();
            int counter = 1;
            for( int i=0; i<s.length(); i++ ) {
                if( s.charAt(i) == ',' ) {
                    counter++;
                }
            }
            mMember.setText(counter+" members");
            TextView update = (TextView) v.findViewById(R.id.tHost);
            update.setVisibility(View.GONE);
           // update.setText("Last update on: ");
            TextView mUpdate = (TextView) v.findViewById(R.id.tHostName);
            mUpdate.setVisibility(View.GONE);
           // mUpdate.setText("Date and Time");


            return v;
        }


    }
    void writeToList(){
        Log.d("Groups: ", groups.toString());
        listView.setAdapter(new MyAdapter(getActivity(), R.layout.item_event, groups));
    }
}