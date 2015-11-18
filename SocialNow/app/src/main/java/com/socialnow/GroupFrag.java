package com.socialnow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by lauamy on 27/10/15.
 */
public class GroupFrag extends Fragment {
    private ListView listView;
    private View v;
    List<String> title;
    List<String> privacy;
    List<Bitmap> photo;
    List<String> member;
    List<Date> update;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        v = inflater.inflate(R.layout.frag_group, container, false);

        listView = (ListView)v.findViewById(R.id.lvGroup);
        title = new LinkedList<String>();
        privacy = new LinkedList<String>();
        photo = new LinkedList<Bitmap>();
        member = new LinkedList<String>();
        update = new LinkedList<Date>();

        return v;

    }

    class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_event,null);
            TextView groupname = (TextView) v.findViewById(R.id.tEname);
            groupname.setText("Group Name");
            ImageView img = (ImageView) v.findViewById(R.id.ivEvent);
            img.setImageBitmap(photo.get(position));
            TextView privacy = (TextView) v.findViewById(R.id.tEdate);
            //privacy.setText(privacy.get(position));
            //privacy.setText(date.get(position).toString());
            //TextView eventlocation = (TextView) v.findViewById(R.id.tElocation);
            //eventlocation.setText(location.get(position));
            //TextView eventhost = (TextView) v.findViewById(R.id.tHostName);
            //eventhost.setText(hostName.get(position));

            writeToList();


            return v;
        }


    }
    void writeToList(){
        listView.setAdapter(new MyAdapter(getActivity(), R.layout.item_event, title));
    }
}