package com.socialnow.Search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.HomeScreen.ProfileFrag;
import com.socialnow.Models.Profile;
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listView.getItemAtPosition(position);
                User user = users.get(position);

                Response.Listener<Profile> response = new Response.Listener<Profile>() {
                    @Override
                    public void onResponse(Profile response) {
                        if (response.getName() != null) {
                            Log.d("Profile", "success " + response.getEmail() + " " + response.getName());
                            ProfileFrag p = new ProfileFrag();
                            Bundle b = new Bundle();
                            b.putSerializable("profile", response);
                            p.setArguments(b);
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.searchresult, p, "fragment")
                                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                    .addToBackStack("search")
                                    .commit();
                        }else{
                            Log.d("Login", "Error: " + response.getUser_token());
                            Log.d("Wrong credentials:", "Not valid username and password");
                        }
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Failed", "Login Failed");

                    }
                };
                API.profileInfo("profile", user.getUser_token(), response, errorListener);


            }
        });
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