package com.socialnow.Search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.socialnow.Models.User;
import com.socialnow.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lauamy on 21/12/15.
 */
public class UserTabFrag extends Fragment {
    private View v;
    private ListView listView;
    private ArrayList<User> users;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_parti, container, false);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fAddComment);
        fab.hide();
        listView = (ListView)v.findViewById(R.id.lvParti);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String bundleValue = bundle.getString("from", "");
            if(bundleValue.equals("Search")){
                users = (ArrayList<User>) bundle.getSerializable("searchUsers");
            }
            else{
                Log.d("error", "error in usertabfrag");
            }
        }else{
            Log.d("error", "error in usertabfrag bundle empty");
        }
        listView.setAdapter(new MyAdapter(getActivity(), R.layout.item_event, users));

        return v;
    }

    class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_member, parent, false);

            TextView mText = (TextView) customView.findViewById(R.id.tvMember);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivMember);

            if(users != null) {
                mText.setText(users.get(position).getName());
                Picasso.with(((Activity) getContext()))
                        .load(users.get(position).getUser_photo())
                        .resize(30, 30)
                        .placeholder(R.drawable.profilpic)
                        .centerCrop()
                        .into(mImg);
            }
            return customView;
        }
    }
}