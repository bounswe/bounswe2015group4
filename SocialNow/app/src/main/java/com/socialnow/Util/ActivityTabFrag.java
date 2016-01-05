package com.socialnow.Util;

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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.socialnow.Models.Profile;
import com.socialnow.Models.User;
import com.socialnow.PartiActivity;
import com.socialnow.R;
import com.socialnow.Users.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by lauamy on 27/10/15.
 */
public class ActivityTabFrag extends Fragment {
    ListView listView;
    ListAdapter mAdapter;
    String[] tvParti;
    public Profile p;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.tabfrag_activity, container, false);

        listView=(ListView)v.findViewById(R.id.lvParti);
        //Profile p = Utils.getCurrentProfile();
        ArrayList<String> names = new ArrayList<>();

        tvParti = new String[p.getNumberOfFollowers()];
        mAdapter = new MemberAdapter(getContext(),R.layout.item_member,p.getUser_followers());
        listView.setDividerHeight(10);
        listView.setAdapter(mAdapter);

        return v;
    }

    public class MemberAdapter extends ArrayAdapter<String> {
        public ArrayList<User> users;
        public MemberAdapter(Context context, int resource, ArrayList<User> users) {
            super(context, R.layout.item_inviteguest, tvParti);
            this.users = users;
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_member, parent, false);
            String item = getItem(position);

            TextView mText = (TextView) customView.findViewById(R.id.tvMember);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivMember);

            mText.setText(users.get(position).getName());
            Picasso.with(((Activity) getContext()))
                    .load(users.get(position).getUser_photo())
                    .resize(30, 30)
                    .placeholder(R.drawable.profilpic)
                    .centerCrop()
                    .into(mImg);

            return customView;

        }

    }
}