package com.socialnow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.*;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by lauamy on 27/10/15.
 */
public class GroupFrag extends Fragment {
    private ListView listView;
    private View v;
    //List<String> title;
    List<String> privacy;
    List<Bitmap> photo;
    List<String> member;
    List<Date> update;
    String [] title = {""};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        v = inflater.inflate(R.layout.frag_group, container, false);

        listView = (ListView)v.findViewById(R.id.lvGroup);
        /*title = new LinkedList<String>();
        privacy = new LinkedList<String>();
        photo = new LinkedList<Bitmap>();
        member = new LinkedList<String>();
        update = new LinkedList<Date>();*/
        writeToList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listView.getItemAtPosition(position);
                Intent i2 = new Intent(getActivity(), GroupActivity.class);
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

    class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_event,null);
            TextView groupname = (TextView) v.findViewById(R.id.tEname);
            groupname.setText("Group Name");
            ImageView img = (ImageView) v.findViewById(R.id.ivEvent);
            img.setImageResource(R.drawable.ic_people);
            ImageView privacy = (ImageView) v.findViewById(R.id.ivEDate);
            privacy.setImageResource(R.drawable.ic_lock);
            TextView mPrivacy = (TextView) v.findViewById(R.id.tEdate);
            mPrivacy.setText("Privacy");
            //privacy.setText(date.get(position).toString());
            ImageView member = (ImageView) v.findViewById(R.id.ivELocation);
            member.setImageResource(R.drawable.groupdrawer);
            TextView mMember = (TextView) v.findViewById(R.id.tElocation);
            mMember.setText("XX members");
            TextView update = (TextView) v.findViewById(R.id.tHost);
            update.setText("Last update on: ");
            TextView mUpdate = (TextView) v.findViewById(R.id.tHostName);
            mUpdate.setText("Date and Time");


            return v;
        }


    }
    void writeToList(){
        listView.setAdapter(new MyAdapter(getActivity(), R.layout.item_event, title));
    }
}