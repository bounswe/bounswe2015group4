package com.socialnow.HomeScreen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.socialnow.MainActivity;
import com.socialnow.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by lauamy on 27/10/15.
 */
public class NotiFrag extends Fragment {
    ListView listView;
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.frag_noti,container,false);
        listView = (ListView)v.findViewById(R.id.notification);
        listView.setAdapter(new MyAdapter(getActivity(), R.layout.item_timeline, MainActivity.notifications));
        return v;
    }

    class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_timeline,null);
            TextView info = (TextView) v.findViewById(R.id.tInfoOwner);
            info.setText(MainActivity.notifications.get(position).getFrom_user().getName() + " " + MainActivity.notifications.get(position).getFrom_user().getSurname() + " ");
            TextView info2 = (TextView) v.findViewById(R.id.tInfoName);
            info2.setText(MainActivity.notifications.get(position).getEvent().getTitle() + " with you.");

            String create_join = " wants to share ";
            TextView info3 = (TextView) v.findViewById(R.id.tInfocreated);
            info3.setText(create_join);



            ImageView img = (ImageView) v.findViewById(R.id.ivEvent);
//            Log.d("events", events.get(position).getEvent_photo() + " " + events.get(position).getEvent_description() + " " + events.get(position).getEvent_participant_users().length);

            if(MainActivity.notifications.get(position).getFrom_user().getUser_photo() == null){
                img.setImageResource(R.drawable.devent);
            }else{
                Picasso.with(((Activity) getContext()))
                        .load(MainActivity.notifications.get(position).getFrom_user().getUser_photo())
                        .resize(80, 80)
                        .placeholder(R.drawable.devent)
                        .centerCrop()
                        .into(img);
            }


            return v;
        }
    }
}