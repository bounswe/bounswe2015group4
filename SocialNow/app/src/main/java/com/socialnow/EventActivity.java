package com.socialnow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class EventActivity extends AppCompatActivity {
    ListView listView;
    TextView description;
    TextView eventdate;
    TextView participantNumber;
    TextView event_host;
    Toolbar toolbar;
    CollapsingToolbarLayout toolBarLayout;
    TextView eventlocation;
    android.support.design.widget.AppBarLayout img;
    String title;
    Date date;
    Bitmap photo;
    String descrip;
    String location;
    String hostName;
    JSONArray parti;
    ArrayList<ParseObject> participants;

    //Dummy Comment List
    int [] ivParti={R.drawable.host,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic};
    String[] tvParti={"User 1","User 2","User 3","User 4","User 5","User 6","User 7"};

    String[] tvComment={"Let me comment on this event.",
            "Let me comment on this event.", "Let me comment on this event.",
            "Let me comment on this event.","Let me comment on this event.",
            "Let me comment on this event.","Let me comment on this event."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
     //   eventname = (TextView) findViewById(R.id.tEname);

        img = (android.support.design.widget.AppBarLayout) findViewById(R.id.app_bar);
    participants = new ArrayList<ParseObject>();
        eventdate = (TextView) findViewById(R.id.tEventDate);
        description = (TextView) findViewById(R.id.tDes);
       eventlocation = (TextView) findViewById(R.id.tEventlocation);
        event_host = (TextView) findViewById(R.id.tEventHost);

      participantNumber = (TextView) findViewById(R.id.tParti);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        parti = new JSONArray();
         toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
       // toolBarLayout.setTitle("Title");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO make it to be a floating action menu for users to share event, make comments and so on......
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.Parti);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewParti = new Intent(getApplicationContext(), PartiActivity.class);
                startActivity(viewParti);
            }
        });

        listView=(ListView)findViewById(R.id.lvComment);
        ListAdapter mAdapter = new MyAdapter(this,R.layout.item_comment,tvParti);
        listView.setAdapter(mAdapter);


        Bundle extras = getIntent().getExtras();
            if(extras == null) {
                title= null;
            } else {
                title= extras.getString("event_title");
                date = (Date)extras.get("event_date");
                location = extras.getString("event_location");
                hostName = extras.getString("host_name");

            }

        getData();

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


    }
    class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter(Context context, int resource, String[] tvParti) {
            super(context, R.layout.item_comment, tvParti);
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_comment, parent, false);

            TextView mText = (TextView) customView.findViewById(R.id.tvParti);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivAuthor);
            TextView mComment = (TextView) customView.findViewById(R.id.tvComment);
            String item = getItem(position);
            mText.setText(item);
            mImg.setImageResource(ivParti[position]);
            mComment.setText(tvComment[position]);
            return customView;

        }

    }

    void getData() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");
        query.whereEqualTo("title", title);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, com.parse.ParseException e) {
                if (object == null) {
                    Log.d("score", "The getFirst request failed.");
                } else {
                    ParseFile fileObject;
                    byte[] data;
                    Log.d("score", "Retrieved the object.");
                    //title = object.getString("title");
                    //date = object.getDate("event_date");
                    //location = object.getString("event_location");
                    descrip = object.getString("event_description");
                    parti= object.getJSONArray("event_members");

                    //hostName = object.getParseObject("event_host").getString("Name") + " " + object.getParseObject("event_host").getString("Surname");
                    fileObject = (ParseFile) object.getParseFile("event_photo");
                    if (fileObject != null) {
                        try {
                            data = fileObject.getData();
                            Bitmap bMap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            photo = bMap;

                            writeToList();

                        } catch (com.parse.ParseException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        Log.d("post", "error retriving posts");
                    }
                }
            }

        });

        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");
        query.whereEqualTo("title", event_title);
        query.findInBackground(new FindCallback() {
                                   @Override
                                   public void done(List objects, com.parse.ParseException e) {
                                   }

                                   @Override
                                   public void done(Object o, Throwable throwable) {
                                       List<ParseObject> myObject = (List<ParseObject>) o;

                                       if (throwable == null) {
                                           int count = 0;
                                           ParseFile fileObject;
                                           byte[] data;

                                           title = myObject.get(0).getString("title");
                                           date = myObject.get(0).getDate("event_date");
                                           location = myObject.get(0).getString("event_location");
                                           hostName = myObject.get(0).getParseObject("event_host").getString("Name") + " " + myObject.get(0).getParseObject("event_host").getString("Surname");
                                           fileObject = (ParseFile) myObject.get(0).getParseFile("event_photo");
                                           if (fileObject != null) {
                                               try {
                                                   data = fileObject.getData();
                                                   Bitmap bMap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                                   photo = bMap;

                                                   //writeToList();

                                               } catch (com.parse.ParseException e1) {
                                                   e1.printStackTrace();
                                               }
                                           }

                                       } else {
                                           Log.d("post", "error retriving posts");
                                       }
                                   }
                               }
        );*/
    }

  void writeToList(){

   //   eventname.setText(title);
      toolBarLayout.setTitle(title);

      Drawable d = new BitmapDrawable(getResources(), photo);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
          img.setBackground(d);
      }
        description.setText(descrip);
      eventdate.setText(date.toString());
        participantNumber.setText(parti.length()+" people are going");
      eventlocation.setText(location);
      event_host.setText(hostName);
    //  TextView eventhost = (TextView) findViewById(R.id.tHostName);
    //  eventhost.setText(hostName);
    }
}