package com.socialnow.Util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.socialnow.Models.Profile;
import com.socialnow.Models.User;
import com.socialnow.R;
import com.socialnow.Users.Utils;

import java.util.ArrayList;


/**
 * Created by lauamy on 27/10/15.
 */
public class AboutTabFrag extends Fragment {
    TextView interests;
    TextView followers;
    TextView followings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.tabfrag_about,container,false);
        interests = (TextView) v.findViewById(R.id.tInterests);
        followers = (TextView) v.findViewById(R.id.tFollowers);
        followings = (TextView) v.findViewById(R.id.tFollowings);

        Profile p = Utils.getCurrentProfile();

        String s = "";
        for(String tag: p.getUser_tags()){
            s += "#" + tag + ", ";
        }
        interests.setText(s);

        followers.setText(p.getNumberOfFollowers());


        followings.setText(p.getNumberOfFollowings());


        return v;
    }

}