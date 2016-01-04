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
import java.util.regex.Pattern;

import java.util.ArrayList;

/**
 * Created by lauamy on 29/10/15.
 */
public class PartiActivity extends AppCompatActivity {
    ListView listView;
    ListAdapter mAdapter;
    User cUser; //current user
    String followers;

    //RelativeLayout itemLayout;


    //TODO this activity should list the user's friends and should be able to give results for other activities.
    //Dummy Parti list with Profile Pics
    int [] mImgArr={R.drawable.host,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic};
    String[] tvParti={"User 1","User 2","User 3","User 4","User 5","User 6","User 7"};
    String mTitle;
    public static ArrayList<String> followersList = new ArrayList<>();
    public static ArrayList<User> groupMembers;
    public static ArrayList<PostDetail> groupPosts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parti);
        listView=(ListView)findViewById(R.id.lvParti);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fAddComment);
        fab.hide();
        cUser = Utils.getCurrentUser();


        followers = cUser.getUser_followers();
        if(followers.isEmpty()) {

            //TODO display members of groups the user attended.

        }else if(followers.length()==1)
        {
            String followerList[]= new String[1];
            followerList[0]= followers;
            followersList =(ArrayList) Arrays.asList(followerList);


        }else
        {
            String followerList[] = followers.split(", ");
            followersList =(ArrayList) Arrays.asList(followerList);

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
                mAdapter = new GuestAdapter(this,R.layout.item_inviteguest,followersList);
                mTitle = "Select";
                break;

            case "EventActivity":
                tvParti = new String[groupMembers.size()];
                mAdapter = new MemberAdapter(this,R.layout.item_parti,groupMembers);
                mTitle = "Participants";
                break;

            case "EditGroupActivity":
                mAdapter = new EditMemberAdapter(this,R.layout.item_editmember,tvParti);
                listView.setAdapter(mAdapter);
                mTitle = "Select";
                break;

            case "Post":
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

            case "Comment":
               /* final long id = getIntent().getLongExtra("group_id", -1);
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
                registerForContextMenu(listView); //View menu by long-click on listview*/
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

    protected static void displayFollowers(String follower_token)
    {
            getData(follower_token);

    }

    protected  static void getData(String s)
    {

        Response.Listener<Profile> response = new Response.Listener<Profile>() {
            @Override
            public void onResponse(Profile response) {

                if(response.getUser_token().isEmpty()
                        ) {
                    Log.d("Profile", "Retrieve user info success " + response);

                }else{
                    Log.d("Leave", "Error: User not found.");
                }

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", "User retrieve failed");

            }
        };
        API.profileInfo("showProfileDetails", s, response, errorListener);
    }

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
    class GuestAdapter extends ArrayAdapter<String> {
        public GuestAdapter(Context context, int resource, ArrayList<String> tvGuest1) {
            super(context, R.layout.item_inviteguest, followersList);
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_inviteguest, parent, false);
            String item = getItem(position);

            TextView mText = (TextView) customView.findViewById(R.id.tvGuest);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivGuest);
            CheckBox mCB = (CheckBox) customView.findViewById(R.id.cGuestInvite);
            mText.setText(item);
            mImg.setImageResource(mImgArr[position]);
            displayFollowers(item);
            return customView;


        }

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
