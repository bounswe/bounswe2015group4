package com.socialnow.Groups;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.apradanas.simplelinkabletext.Link;

import com.socialnow.API.API;
import com.socialnow.Models.Event;
import com.socialnow.Models.Group;
import com.socialnow.Models.Group_Detail;
import com.socialnow.Models.Post;
import com.socialnow.Models.PostDetail;
import com.socialnow.Models.User;
import com.socialnow.PartiActivity;
import com.socialnow.R;
import com.socialnow.Search.SearchUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;


public class GroupActivity extends AppCompatActivity {
    ListView listView;
    TextView description,tPrivacy;
    TextView mCreatedate;
    TextView mMember;
    TextView comment;
    TextView mOwner;
    Toolbar toolbar;
    com.apradanas.simplelinkabletext.LinkableTextView tags;
    CollapsingToolbarLayout toolBarLayout;
    TextView mPrivacy;
    android.support.design.widget.AppBarLayout img;
    Long groupId;
    Group_Detail myGroup = new Group_Detail();
    JSONArray parti;

    ImageView privacy;
    ArrayList<User> groupMembers;
    ArrayList<PostDetail> groupPosts;
    List<Link> links;
    private ArrayList<String> tags_event = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        tPrivacy = (TextView) findViewById(R.id.tPrivacy);
        tags = (com.apradanas.simplelinkabletext.LinkableTextView) findViewById(R.id.tags);
        //toolBarLayout.setTitle("Group Name");
        comment = (TextView) findViewById(R.id.tComment);
        img = (android.support.design.widget.AppBarLayout) findViewById(R.id.app_bar);

        description = (TextView) findViewById(R.id.tDes);
        mPrivacy = (TextView) findViewById(R.id.tPrivacy);

        mMember = (TextView) findViewById(R.id.tMember);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        parti = new JSONArray();
        toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        // toolBarLayout.setTitle("Title");


        RelativeLayout viewMember=(RelativeLayout)findViewById(R.id.Member);
        viewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent viewParti = new Intent(getApplicationContext(), PartiActivity.class);
                //startActivity(viewParti);
                Intent i = new Intent(getApplicationContext(), PartiActivity.class).putExtra("from", "GroupActivity");
                PartiActivity.groupMembers = groupMembers;
                startActivity(i);
            }
        });

        RelativeLayout viewComment=(RelativeLayout)findViewById(R.id.Comment);
        viewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent viewParti = new Intent(getApplicationContext(), PartiActivity.class);
                //startActivity(viewParti);
                Intent i2 = new Intent(getApplicationContext(), PartiActivity.class).putExtra("from", "PostGroup");
                i2.putExtra("group_id", myGroup.getId());
                Log.d("id ga", myGroup.getId() + "s");
                PartiActivity.groupPosts = groupPosts;
                startActivity(i2);
            }
        });



        Bundle extras = getIntent().getExtras();
        if(extras == null) {

        } else {
            groupId = extras.getLong("id");

        }

        //getData();

      /*  TextView eventname = (TextView) findViewById(R.id.tEname);
        eventname.setText(title);
        ImageView img = (ImageView) findViewById(R.id.ivEvent);
        img.setImageBitmap(photo);
        TextView eventdate = (TextView) findViewById(R.id.tEdate);
        eventdate.setText(date.toString());
        TextView eventlocation = (TextView) findViewById(R.id.tElocation);
        eventlocation.setText(location);
        TextView eventhost = (TextView) findViewById(R.id.tHostName);
        eventhost.setText(hostName);*/
        toolBarLayout.setTitle("Group Name");

        final Activity a = this;
        Link linkHashtag = new Link(Pattern.compile("(\\w+)"))
                .setUnderlined(true)
                .setTextStyle(Link.TextStyle.BOLD)
                .setClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String text) {
                        //TODO navigate to search with tag page
                        SearchUtil.searchAnOpenActivity(text, a);
                        Toast.makeText(GroupActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                });

        links = new ArrayList<>();
        links.add(linkHashtag);

        getData();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group, menu);
        return true;
    }

  void getData(){
      Response.Listener<Group_Detail> response = new Response.Listener<Group_Detail>() {
          @Override
          public void onResponse(Group_Detail response) {
              if(response != null) {
                  myGroup = response;
                  groupMembers = myGroup.getGroup_members();
                  //groupMembers.add(0, myGroup.getOwner());
                  groupPosts = myGroup.getGroup_posts();
                  tags_event = myGroup.getTags();
                  Log.d("Group", response.toString());
                  writeToList();
              }else{
                  Log.d("Group", "error");
              }
          }
      };

      Response.ErrorListener errorListener = new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Log.d("Failed", error.toString());

          }
      };

      API.getGroupDetail("getGroupDetail", groupId, response, errorListener);
    }
    void writeToList(){
        description.setText(myGroup.getGroup_description());
        toolBarLayout.setTitle(myGroup.getGroup_name());
        Drawable d = new BitmapDrawable(getResources(), getBitmapFromURL(myGroup.getGroup_photo()));
        img.setBackground(d);
        int memberNumer = myGroup.getGroup_members().size();
        mMember.setText(memberNumer +" Members");
        comment.setText(myGroup.getGroup_posts().size()+  " Posts");
        String tagString = "";
        for(int i= 0; i<tags_event.size();i++){
            tagString += tags_event.get(i)+" ";
        }
        tags.setText(tagString).addLinks(links).build();
        tPrivacy.setText(myGroup.getVisibleTo().toString());

    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
    @Override
    public void onRestart() {
        super.onRestart();
        getData();
        Log.d("Dene","Haydi");
    }

}