package com.socialnow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.Groups.EditGroupActivity;
import com.socialnow.Models.Event;
import com.socialnow.Models.PostDetail;
import com.socialnow.Models.Profile;
import com.socialnow.Models.User;
import com.squareup.picasso.Picasso;
import com.socialnow.Users.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import java.util.ArrayList;

/**
 * Created by lauamy on 29/10/15.
 */
public class PartiActivity extends AppCompatActivity {
    ListView listView;
    ListAdapter mAdapter;
    User cUser; //current user
    Profile p;
    Profile pCurrent;
    public static ArrayList<User> followerList = new ArrayList<>();
    public static ArrayList<User> followingList = new ArrayList<>();
    int [] mImgArr={R.drawable.host,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic};
    String[] tvParti={"User 1","User 2","User 3","User 4","User 5","User 6","User 7"};
    String mTitle;
    public static ArrayList<User> groupMembers;
    public static ArrayList<PostDetail> groupPosts;
    public static ArrayList<PostDetail> eventPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parti);
        listView=(ListView)findViewById(R.id.lvParti);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fAddComment);
        fab.hide();
        cUser = Utils.getCurrentUser();
        pCurrent = Utils.getCurrentProfile();

        ArrayList<String> uTokens = new ArrayList<>();
        followerList = pCurrent.getUser_followers();
        followingList = pCurrent.getUser_following();

        for(User u: followerList)
        {
           uTokens.add(u.getUser_token());
        }

        for( User u: followingList)
        {
            if(!uTokens.contains(u.getUser_token()))
            {
                followerList.add(u);

            }
        }





        // Logic to check the calling activity
        String callingActivity = getIntent().getStringExtra("from");


        switch (callingActivity) {
            case "GroupActivity":
                mTitle = "Members";
                tvParti = new String[groupMembers.size()];
                mAdapter = new MemberAdapter(this,R.layout.item_member,groupMembers);
                break;

            case "EditEventActivity":


                //TODO display members of groups the user attended.
                fab.show();
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), CommentActivity.class).putExtra("flag", "addReply");
                        startActivity(i);
                    }
                });

                if(followerList.size() !=0)
                {
                    mAdapter = new GuestAdapter(this,R.layout.item_inviteguest,followerList);
                    listView.setDividerHeight(10);
                    listView.setAdapter(mAdapter);
                    mTitle = "Select";
                    registerForContextMenu(listView); //View menu by long-click on listview



                }
                break;

            case "EventActivity":
                tvParti = new String[groupMembers.size()];
                mAdapter = new MemberAdapter(this,R.layout.item_parti,groupMembers);
                mTitle = "Participants";
                break;

            case "SearchResultsUser":
                tvParti = new String[groupMembers.size()];
                mAdapter = new MemberAdapter(this,R.layout.item_parti,groupMembers);
                mTitle = "Participants";
                break;

            case "EditGroupActivity":


                //TODO display members of groups the user attended.
                fab.show();
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), CommentActivity.class).putExtra("flag", "addReply");
                        startActivity(i);
                    }
                });

                if(followerList.size() !=0)
                {
                    mAdapter = new GuestAdapter(this,R.layout.item_inviteguest,followerList);
                    listView.setDividerHeight(10);
                    listView.setAdapter(mAdapter);
                    mTitle = "Select";
                    registerForContextMenu(listView); //View menu by long-click on listview



                }
                break;


            case "PostGroup":
                final long id = getIntent().getLongExtra("group_id", -1);
                Log.d("pa", id + "s");
                fab.show();
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), CommentActivity.class).putExtra("flag", "addComment");
                        i.putExtra("group_id", id);
                        startActivity(i);
                    }
                });
                tvParti = new String[groupPosts.size()];
                mAdapter = new CommentAdapter(this,R.layout.item_comment,groupPosts);
                listView.setDividerHeight(10);
                listView.setAdapter(mAdapter);
                mTitle = "Posts";
                registerForContextMenu(listView); //View menu by long-click on listview
                break;

            case "PostEvent":
                final long idE = getIntent().getLongExtra("event_id", -1);
                Log.d("pa", idE + "s");
                fab.show();
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), CommentActivity.class).putExtra("flag", "addComment");
                        i.putExtra("event_id", idE);
                        startActivity(i);
                    }
                });
                tvParti = new String[eventPosts.size()];
                mAdapter = new CommentAdapter(this,R.layout.item_comment,eventPosts);
                listView.setDividerHeight(10);
                listView.setAdapter(mAdapter);
                mTitle = "Posts";
                registerForContextMenu(listView); //View menu by long-click on listview
                break;

            case "Reply":
                fab.show();
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), CommentActivity.class).putExtra("flag", "addReply");
                        startActivity(i);
                    }
                });

                mAdapter = new CommentAdapter(this,R.layout.item_comment,tvParti);
                listView.setDividerHeight(10);
                listView.setAdapter(mAdapter);
                mTitle = "Replies";


            default:
                break;
        }
        listView.setAdapter(mAdapter);
        getSupportActionBar().setTitle(mTitle);



        //Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

    }

   /* protected void retrieveFollower(String follower_token)
    {

        Response.Listener<Profile> response = new Response.Listener<Profile>() {
            @Override
            public void onResponse(Profile response) {
                if(response != null) {
                    Log.d("Profile info", response.toString());
                    p = new Profile(response.getUser_interest_groups(), response.getUser_followers(), response.getUser_following(),response.getUser_tags(), response.getUser_participating_events(), response.getEmail(), response.getName(), response.getNumberOfFollowers(), response.getNumberOfFollowings(), response.getSurname(), response.getRole(), response.getUser_token());

                }

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Profile not retrieved", error.toString());

            }
        };


        API.profileInfo("showProfileDetails", follower_token,response, errorListener);


    }*/



    class PartiAdapter extends ArrayAdapter<String> {
        public PartiAdapter(Context context, int resource, String[] tvParti) {
            super(context, R.layout.item_parti, tvParti);
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_parti, parent, false);
            String item = getItem(position);

            TextView mText = (TextView) customView.findViewById(R.id.tvParti);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivParti);
            mText.setText(item);
            mImg.setImageResource(mImgArr[position]);
            return customView;


        }

    }
    class GuestAdapter extends ArrayAdapter<User> {

        public GuestAdapter(Context context, int resource, ArrayList<User> tvGuest1) {
            super(context, R.layout.item_inviteguest, followerList);

        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_inviteguest, parent, false);

            TextView mText = (TextView) customView.findViewById(R.id.tvGuest);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivGuest);
            CheckBox mCB = (CheckBox) customView.findViewById(R.id.cGuestInvite);

            //TODO send notification to invited users

           /* Button invite = (Button) customView.findViewById(R.id.inviteButton);
            invite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     Intent i = new Intent(getApplicationContext(), CommentActivity.class).putExtra("flag", "addReply");
                     startActivity(i);
                }
            });*/


                mText.setText(followerList.get(position).getName());
                Picasso.with(((Activity) getContext()))
                        .load(followerList.get(position).getUser_photo())
                        .resize(30, 30)
                        .placeholder(R.drawable.profilpic)
                        .centerCrop()
                        .into(mImg);

            return customView;


        }

    }
    @Override
    public void onRestart() {
        super.onRestart();
        //TODO
        finish();
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

    /*class EditMemberAdapter extends ArrayAdapter<String> {
        public EditMemberAdapter(Context context, int resource, String[] tvParti) {
            super(context, R.layout.item_editmember, tvParti);
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_editmember, parent, false);
            String item = getItem(position);

            TextView mText = (TextView) customView.findViewById(R.id.tvMember);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivMember);
            CheckBox mCB = (CheckBox) customView.findViewById(R.id.cAddMember);
            mText.setText(item);
            mImg.setImageResource(mImgArr[position]);
            ToggleButton toggle=(ToggleButton)findViewById(R.id.tbModerator);

            return customView;

        }

    }*/
    class EditMemberAdapter extends ArrayAdapter<String> {
        public EditMemberAdapter(Context context, int resource, String[] tvParti) {
            super(context, R.layout.item_editmember, tvParti);
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_editmember, parent, false);
            String item = getItem(position);

            TextView mText = (TextView) customView.findViewById(R.id.tvMember);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivMember);
            CheckBox mCB = (CheckBox) customView.findViewById(R.id.cAddMember);
            mText.setText(item);
            mImg.setImageResource(mImgArr[position]);

            mCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ToggleButton toggle = (ToggleButton) findViewById(R.id.tbModerator);
                    if (isChecked)
                        toggle.setVisibility(View.VISIBLE);
                    else
                        toggle.setVisibility(View.GONE);
                }
            });

            return customView;

        }

    }

    class CommentAdapter extends ArrayAdapter<String> {
        ArrayList<PostDetail> posts;
        public CommentAdapter(Context context, int resource, ArrayList<PostDetail> posts) {
            super(context, R.layout.item_comment, tvParti);
            this.posts = posts;
        }
        public CommentAdapter(Context context, int resource, String[] tvParti) {
            super(context, R.layout.item_inviteguest, tvParti);

        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_comment, parent, false);
            String item = getItem(position);

            TextView mText = (TextView) customView.findViewById(R.id.tvParti);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivAuthor);
            TextView mComment = (TextView) customView.findViewById(R.id.tvComment);
            if(posts != null) {
                mText.setText(posts.get(position).getOwner().getName() + " " + posts.get(position).getOwner().getSurname());
                Picasso.with(((Activity) getContext()))
                        .load(posts.get(position).getOwner().getUser_photo())
                        .resize(30, 30)
                        .placeholder(R.drawable.profilpic)
                        .centerCrop()
                        .into(mImg);
                mComment.setText(posts.get(position).getContent());
            }
            return customView;
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_comment, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.action_view:
                Intent view = new Intent(getApplicationContext(), PartiActivity.class).putExtra("from", "Reply");
                startActivity(view);

                break;

            case R.id.action_reply:
                Intent reply = new Intent(getApplicationContext(), CommentActivity.class).putExtra("flag", "addReply");
                startActivity(reply);
                break;

            case R.id.action_share:
                break;

            case R.id.action_report:
                break;

            case R.id.action_edit:
                Intent edit = new Intent(getApplicationContext(), CommentActivity.class).putExtra("flag","Edit");
                startActivity(edit);
                break;


            default:
                break;
        }
        return true;
    }
}
