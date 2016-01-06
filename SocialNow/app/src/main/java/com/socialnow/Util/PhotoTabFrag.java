package com.socialnow.Util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.HomeScreen.ProfileFrag;
import com.socialnow.Models.Profile;
import com.socialnow.Models.User;
import com.socialnow.R;
import com.socialnow.Users.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by lauamy on 27/10/15.
 */
public class PhotoTabFrag extends Fragment {
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

        tvParti = new String[p.getNumberOfFollowings()];
        mAdapter = new MemberAdapter(getContext(),R.layout.item_member,p.getUser_following());
        listView.setDividerHeight(10);
        listView.setAdapter(mAdapter);
        setListViewForProfileClick(p.getUser_following());
        return v;
    }

    public void setListViewForProfileClick(final ArrayList<User> users){
        final FragmentManager fg = getActivity().getSupportFragmentManager();
        final PhotoTabFrag f = this;
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
                            fg.beginTransaction()
                                    .replace(R.id.fragprofile, p, "fragment")
                                    .hide(f)
                                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                                    .addToBackStack("profileFollower")
                                    .commit();
//                            listView.setVisibility(View.INVISIBLE);
                        } else {
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

