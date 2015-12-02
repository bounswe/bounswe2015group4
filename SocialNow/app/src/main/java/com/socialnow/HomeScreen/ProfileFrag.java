package com.socialnow.HomeScreen;

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
import com.socialnow.Models.User;

import com.astuetz.PagerSlidingTabStrip;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.socialnow.Models.User;
import com.socialnow.PagerAdapter;
import com.socialnow.R;

import java.text.ParseException;

import android.support.v4.app.FragmentStatePagerAdapter;


public class ProfileFrag extends Fragment {

    private static final int SELECTED_PICTURE = 1;

    Button logout;
    User current_user;
    Long user_id;
    ImageView profile_picture;
    TextView user_name;
    TextView user_email;
    String userName;
    String userEmail;
    Bitmap photo;



    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v =  inflater.inflate(R.layout.frag_profile,container,false);
        profile_picture = (ImageView) v.findViewById(R.id.profilepicture);
        user_name = (TextView) v.findViewById(R.id.tUserName);
        user_email = (TextView) v.findViewById(R.id.tUserEmail);
        current_user = new User();

        Context context = this.getActivity();
        SharedPreferences sharedPref =   PreferenceManager.getDefaultSharedPreferences(context);

        user_id= sharedPref.getLong("current_user_id", 1);
        userName = sharedPref.getString("current_user_name", "asdfg");
        userEmail = sharedPref.getString("current_user_email", "şlkjh");

        user_name.setText(userName);
        user_email.setText(userEmail);


        current_user.setId(user_id);

        Response.Listener<User> response = new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                if(response.getId() != -1) {
                    Log.d("Login", "Login success" + response.getEmail() + " " + response.getName());

                    // user_name.setText(response.getName());
                   // user_email.setText(response.getEmail());

                    //TODO CASH USER LOGIN
                    //TODO OPEN HOMEPAGE

                } else {
                    Log.d("ProfilePage", "Error: " + response.getUser_token());
                    Log.d("ProfilePage error:", "error response");
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", "Login Failed");


            }
        };

      /*  fileObject = (ParseFile) current_user.getParseFile("Profile_Picture");
        byte[] data;



        if (fileObject != null) {
            try {
                data = fileObject.getData();
                Bitmap bMap = BitmapFactory.decodeByteArray(data, 0, data.length);
                photo = bMap;

                Drawable d = new BitmapDrawable(getResources(), photo);

                profile_picture.setImageBitmap(photo);

                //writeToList();

            } catch (com.parse.ParseException e1) {
                e1.printStackTrace();
            }
        } else {
            Log.d("post", "error retriving posts");
        }*/



        //below is required when setting profile picture.
        //Intent i2 = new Intent(getActivity(), ProfilePage.class);
        //startActivity(i2);

      // getData();

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("ABOUT"));
        tabLayout.addTab(tabLayout.newTab().setText("ACTIVITIES"));
        tabLayout.addTab(tabLayout.newTab().setText("PHOTOS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
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



   /*void getData() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("username", userName);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, com.parse.ParseException e) {
                if (object == null) {
                    Log.d("score", "The getFirst request failed.");
                } else {
                    ParseFile fileObject;
                    byte[] data;
                    Log.d("score", "Retrieved the object.");

                    //hostName = object.getParseObject("event_host").getString("Name") + " " + object.getParseObject("event_host").getString("Surname");
                    fileObject = (ParseFile) object.getParseFile("Profile_Picture");


                 if (fileObject != null) {
                        try {
                            data = fileObject.getData();
                            Bitmap bMap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            photo = bMap;

                            Drawable d = new BitmapDrawable(getResources(), photo);

                            profile_picture.setImageBitmap(photo);


                            //writeToList();

                        } catch (com.parse.ParseException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        Log.d("post", "error retriving posts");
                    }
                }
            }

        });

    }*/

    void writeToList(){


    }
    public void changeToolBar()
    {

    }
}