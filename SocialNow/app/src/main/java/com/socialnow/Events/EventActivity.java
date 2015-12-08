package com.socialnow.Events;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.socialnow.API.API;
import com.socialnow.Models.Event;
import com.socialnow.Models.Event_Detail;
import com.socialnow.Models.User;
import com.socialnow.PartiActivity;
import com.socialnow.R;
import com.socialnow.Users.Utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    TextView eventname;
    Toolbar toolbar;
    CollapsingToolbarLayout toolBarLayout;
    TextView eventlocation;
    android.support.design.widget.AppBarLayout img;
    String title;
    String date;
    String photo;
    String descrip;
    String location;
    String hostName;
    ArrayList<User> parti;
    int parti_number;
    Long id;
    Event_Detail e;
    byte[] data;

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
        img = (android.support.design.widget.AppBarLayout) findViewById(R.id.app_bar);

        eventdate = (TextView) findViewById(R.id.tEventDate);
        description = (TextView) findViewById(R.id.tDes);
       eventlocation = (TextView) findViewById(R.id.tEventlocation);
        event_host = (TextView) findViewById(R.id.tEventHost);

      participantNumber = (TextView) findViewById(R.id.tParti);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
       // toolBarLayout.setTitle("Title");
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            title= null;
        } else {
            title= extras.getString("title");
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
            date = ft.format(new Date(extras.getLong("date")));
            location = extras.getString("location");
            hostName = extras.getString("hostname");
            descrip = extras.getString("description");
            photo = extras.getString("photo");

            id = extras.getLong("id");

        }

        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.Parti);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewParti = new Intent(getApplicationContext(), PartiActivity.class).putExtra("from", "EventActivity");
                PartiActivity.groupMembers = e.getEvent_participants();
                startActivity(viewParti);
            }
        });

        RelativeLayout viewComment=(RelativeLayout)findViewById(R.id.Comment);
        viewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent viewParti = new Intent(getApplicationContext(), PartiActivity.class);
                //startActivity(viewParti);
                //Intent i2 = new Intent(getApplicationContext(), PartiActivity.class).putExtra("from", "Comment");
                //startActivity(i2);
            }
        });

        boolean participated = false;
        if(Utils.getCurrentProfile().getUser_participating_events() != null)
            for(Event ee : Utils.getCurrentProfile().getUser_participating_events()) {
                if (ee.getId() == id) {
                    participated = true;
                    break;
                }
            }


        final RelativeLayout viewJoin=(RelativeLayout)findViewById(R.id.Join);
        if(participated){
            viewJoin.setBackgroundResource(R.drawable.purplebutton);
            TextView tv = (TextView) findViewById(R.id.tJoin);
            tv.setText("LEAVE");
        }

        viewJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent viewParti = new Intent(getApplicationContext(), PartiActivity.class);
                //startActivity(viewParti);
                boolean participated = false;
                Log.d("asd", (Utils.getCurrentProfile() == null) + "ad");
                Log.d("asd", "ad" + (Utils.getCurrentProfile().getUser_participating_events() == null));
                if(Utils.getCurrentProfile().getUser_participating_events() != null)
                 for(Event ee : Utils.getCurrentProfile().getUser_participating_events()) {
                     Log.d("asd", "ad" + ee.getId() + "asd " + e.getId());
                     if (ee.getId() == id) {
                         participated = true;
                         break;
                     }
                 }

                if(!participated) {
                    joinToTheGroup();
                    viewJoin.setBackgroundResource(R.drawable.purplebutton);
                }else {
                    leaveTheGroup();
                    viewJoin.setBackgroundResource(R.drawable.cyanbutton);
                }
            }
        });




       // e = extras.getParcelable("Event");

        getData();



        writeToList();

    }

    private void leaveTheGroup() {
        TextView tv = (TextView) findViewById(R.id.tJoin);
        tv.setText("JOIN");
        Response.Listener<Event> response = new Response.Listener<Event>() {
            @Override
            public void onResponse(Event response) {
                if(response.getId() != -1) {
                    Log.d("Event", "Leave success " + response.getEvent_description());


                    parti_number--;
                    participantNumber.setText(parti_number+" people are going");


                    Utils.updateProfile();
                    getData();

                    ImageView v = (ImageView) findViewById(R.id.ivArrow4);
                    v.setImageDrawable(getResources().getDrawable(R.drawable.rightarrow));
                }else{
                    Log.d("Leave", "Error: Unknown");
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", "Leave Failed");

            }
        };
        API.leaveEvent("leaveEvent", e.getId(), response, errorListener);
    }

    private void joinToTheGroup() {
        TextView tv = (TextView) findViewById(R.id.tJoin);
        tv.setText("LEAVE");
        Response.Listener<Event> response = new Response.Listener<Event>() {
            @Override
            public void onResponse(Event response) {
                if(response.getId() != -1) {
                    Log.d("Event", "Join success " + response.getEvent_description());
                    Utils.updateProfile();
                    getData();

                    parti_number++;
                    participantNumber.setText(parti_number + " people are going");


                    ImageView v = (ImageView) findViewById(R.id.ivArrow4);
                    v.setImageDrawable(getResources().getDrawable(R.drawable.leftarrow));
                }else{
                    Log.d("Join", "Error: Unknown");
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", "Join Failed");

            }
        };
        API.joinEvent("joinEvent", e.getId(), response, errorListener);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    void getData() {

        Response.Listener<Event_Detail> response = new Response.Listener<Event_Detail>() {
            @Override
            public void onResponse(Event_Detail response) {
                if(response != null) {
                    Log.d("Event", response.toString());
                    e = response;
                    parti = response.getEvent_participants();
                    Log.d("event participants: " , parti.toString());
                    parti_number = parti.size();
                    participantNumber.setText(parti_number+" people are going");


                }else{
                    Log.d("Event", "error");
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", error.toString());

            }
        };

        API.getEventDetail("getEventDetail", id, response, errorListener);
    }

    Drawable d;
  void writeToList(){


      toolBarLayout.setTitle(title);

    /*  if(photo!=null){
          Drawable d = new BitmapDrawable(getResources(), photo);
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
              img.setBackground(d);z
          }
      }*/

      description.setText(descrip);
      eventdate.setText(date);
      participantNumber.setText(parti_number+" people are going");


      eventlocation.setText(location);
      event_host.setText(hostName);

      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

      StrictMode.setThreadPolicy(policy);

      new Thread(new Runnable() {
          @Override
          public void run() {
              d = new BitmapDrawable(getResources(), getBitmapFromURL(photo));
          }
      }).start();
      d = new BitmapDrawable(getResources(), getBitmapFromURL(photo));
      img.setBackground(d);

    //  TextView eventhost = (TextView) findViewById(R.id.tHostName);
    //  eventhost.setText(hostName);
    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            Log.d("src", src + "asd");
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

}