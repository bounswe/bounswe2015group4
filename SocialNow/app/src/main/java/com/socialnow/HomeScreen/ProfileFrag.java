package com.socialnow.HomeScreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.App;

import com.socialnow.API.API;
import com.socialnow.Models.Profile;
import com.socialnow.Models.User;

import com.astuetz.PagerSlidingTabStrip;

import com.socialnow.Models.User;
import com.socialnow.PagerAdapter;
import com.socialnow.R;
import com.socialnow.Users.Utils;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;

import android.support.v4.app.FragmentStatePagerAdapter;


public class ProfileFrag extends Fragment {

    private static final int SELECTED_PICTURE = 1;
    Profile profileToShow;
    Button bFollow;
    User current_user;
    Long user_id;
    ImageView profile_picture;
    TextView user_name;
    TextView user_email;
    TextView user_role;
    String userName;
    String userEmail;
    String userPhoto;
    String userRole;
    Bitmap photo;



    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v =  inflater.inflate(R.layout.frag_profile,container,false);
        profile_picture = (ImageView) v.findViewById(R.id.profilepicture);

        user_name = (TextView) v.findViewById(R.id.tUserName);
        user_email = (TextView) v.findViewById(R.id.tUserEmail);
        user_role = (TextView) v.findViewById(R.id.tRole);
        current_user = Utils.getCurrentUser();
        bFollow = (Button) v.findViewById(R.id.bFollow);
        bFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followUnfollow();
            }
        });
        Context context = this.getActivity();
        SharedPreferences sharedPref =   PreferenceManager.getDefaultSharedPreferences(context);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            profileToShow = (Profile)bundle.getSerializable("profile");
            if(!profileToShow.getUser_token().equals(Utils.getCurrentProfile().getUser_token())){
                if(isFolllowing()){
                    bFollow.setText("Unfollow");
                }
            }else{
                bFollow.setVisibility(View.GONE);
            }
        }else{
            profileToShow = Utils.getCurrentProfile();
            bFollow.setVisibility(View.GONE);
        }

//        user_id= Utils.getCurrentUser().getId();
        userName = profileToShow.getName();
        userEmail = profileToShow.getEmail();
        userPhoto = profileToShow.getUser_photo();
        userRole = profileToShow.getRole();

        user_name.setText(userName);
        user_email.setText(userEmail);
        user_role.setText(userRole);


        Log.d("photo",userPhoto + "asd");
        if(userPhoto == null)
            userPhoto = "asd";
        Picasso.with(getContext())
                .load(userPhoto)
                .resize(80, 80)
                .placeholder(R.drawable.devent)
                .centerCrop()
                .into(profile_picture);

//        current_user.setId(user_id);






        //below is required when setting profile picture.
        //Intent i2 = new Intent(getActivity(), ProfilePage.class);
        //startActivity(i2);

      // getData();

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("ABOUT"));
        tabLayout.addTab(tabLayout.newTab().setText("FOLLOWERS"));
        tabLayout.addTab(tabLayout.newTab().setText("FOLLOWINGS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount(), profileToShow);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        return v;
    }
    public void followUnfollow(){
        String postBody = "{user_token: "+ Utils.getCurrentUser().getUser_token()+" , user_token_follow: " + profileToShow.getUser_token()+" }";
        if(bFollow.getText().equals("Follow")){
            Response.Listener<User> response = new Response.Listener<User>() {
                @Override
                public void onResponse(User response) {
                    if(response.getId() != -1) {
                        Log.d("Follow", "Following success " + response.getName());
                        bFollow.setText("Unfollow");
                    }else{
                        Log.d("Follow", "Error: Unknown");
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Failed", "Creation Failed");

                }
            };
            API.followUser("followUser", postBody, response, errorListener);

        }else{
            Response.Listener<User> response = new Response.Listener<User>() {
                @Override
                public void onResponse(User response) {
                    if(response.getId() != -1) {
                        Log.d("Unfollow", "Unfollowing success " + response.getName());
                        bFollow.setText("Follow");
                    }else{
                        Log.d("UnFollow", "Error: Unknown");
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Failed", "Creation Failed");

                }
            };
            API.unFollowUser("unFollowUser", postBody, response, errorListener);
        }
    }
    public Boolean isFolllowing(){

        Boolean check = false;
        for(User u:profileToShow.getUser_followers()){
            if(u.getUser_token().equals(Utils.getCurrentProfile().getUser_token())){
                check =true;

            }
        }
        return check;
    }
}