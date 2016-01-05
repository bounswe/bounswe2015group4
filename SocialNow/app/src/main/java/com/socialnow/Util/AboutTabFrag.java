package com.socialnow.Util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apradanas.simplelinkabletext.Link;
import com.apradanas.simplelinkabletext.LinkableTextView;
import com.socialnow.Models.Profile;
import com.socialnow.Models.User;
import com.socialnow.R;
import com.socialnow.Users.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by lauamy on 27/10/15.
 */
public class AboutTabFrag extends Fragment {
    LinkableTextView interests;
    TextView followers;
    TextView followings;
    List<Link> links;
    public Profile p;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.tabfrag_about,container,false);
        interests = (LinkableTextView) v.findViewById(R.id.tInterests);
        followers = (TextView) v.findViewById(R.id.tFollower);
        followings = (TextView) v.findViewById(R.id.tFollowing);

        //Profile p = Utils.getCurrentProfile();

        String s = "";
        for(String tag: p.getUser_tagsOnce()){
            s += "#" + tag + " ";
        }


        followers.setText("" + p.getNumberOfFollowers());


        followings.setText("" + p.getNumberOfFollowings());

        Link linkHashtag = new Link(Pattern.compile("(\\w+)"))
                .setUnderlined(true)
                .setTextStyle(Link.TextStyle.BOLD)
                .setClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String text) {
                        //TODO navigate to search with tag page
                       // Toast.makeText(GroupActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                });

        links = new ArrayList<>();
        links.add(linkHashtag);

        interests.setText(s).addLinks(links).build();
        return v;
    }

}