package com.socialnow.Events;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.apradanas.simplelinkabletext.Link;
import com.socialnow.API.API;
import com.socialnow.Models.Event;
import com.socialnow.Models.Event_Detail;
import com.socialnow.Models.PostDetail;
import com.socialnow.Models.User;
import com.socialnow.PartiActivity;
import com.socialnow.R;
import com.socialnow.Users.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


public class EventActivity extends AppCompatActivity {

    TextView description;
    com.apradanas.simplelinkabletext.LinkableTextView tags;
    TextView eventdate, tComment;
    TextView participantNumber;
    TextView event_host;
    Toolbar toolbar;
    CollapsingToolbarLayout toolBarLayout;
    TextView eventlocation;
    android.support.design.widget.AppBarLayout img;
    String title;
    String date;
    String photo;
    String event_description;
    String location;
    User host;
    List<Link> links;
    ArrayList<User> participants;
    private ArrayList<String> tags_event;
    int number_of_participants;
    Long id;
    Event_Detail e;
    ArrayList<PostDetail> eventPosts;



    //Dummy Comment List
    int [] ivParti={R.drawable.host,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic};


    String[] tvComment={"Let me comment on this event.",
            "Let me comment on this event.", "Let me comment on this event.",
            "Let me comment on this event.","Let me comment on this event.",
            "Let me comment on this event.","Let me comment on this event."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        img = (android.support.design.widget.AppBarLayout) findViewById(R.id.app_bar);
        tags_event = new ArrayList<>();
        eventdate = (TextView) findViewById(R.id.tEventDate);
        description = (TextView) findViewById(R.id.tDes);
        eventlocation = (TextView) findViewById(R.id.tEventlocation);
        event_host = (TextView) findViewById(R.id.tEventHost);
        tags = (com.apradanas.simplelinkabletext.LinkableTextView) findViewById(R.id.tags);
        participantNumber = (TextView) findViewById(R.id.tParti);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tComment = (TextView) findViewById(R.id.tComment);
        toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        // toolBarLayout.setTitle("Title");


        Bundle extras = getIntent().getExtras();
        if(extras == null) {

        } else {

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
                Intent i2 = new Intent(getApplicationContext(), PartiActivity.class).putExtra("from", "PostEvent");
                i2.putExtra("event_id", e.getId());
                PartiActivity.eventPosts = eventPosts;
                startActivity(i2);
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
                if (Utils.getCurrentProfile().getUser_participating_events() != null)
                    for (Event ee : Utils.getCurrentProfile().getUser_participating_events()) {
                        Log.d("asd", "ad" + ee.getId() + "asd " + e.getId());
                        if (ee.getId() == id) {
                            participated = true;
                            break;
                        }
                    }

                if (!participated) {
                    joinToTheGroup();
                    viewJoin.setBackgroundResource(R.drawable.purplebutton);
                } else {
                    leaveTheGroup();
                    viewJoin.setBackgroundResource(R.drawable.cyanbutton);
                }
            }
        });


        Link linkHashtag = new Link(Pattern.compile("(\\w+)"))
                .setUnderlined(true)
                .setTextStyle(Link.TextStyle.BOLD)
                .setClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String text) {
                        //TODO navigate to search with tag page
                        Toast.makeText(EventActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                });

        links = new ArrayList<>();
        links.add(linkHashtag);


        getData();


    }

    private void leaveTheGroup() {
        TextView tv = (TextView) findViewById(R.id.tJoin);
        tv.setText("JOIN");
        Response.Listener<Event> response = new Response.Listener<Event>() {
            @Override
            public void onResponse(Event response) {
                if(response.getId() != -1) {
                    Log.d("Event", "Leave success " + response.getEvent_description());
                    number_of_participants--;
                    participantNumber.setText(number_of_participants + " people are going");
                    Utils.updateProfile();
                    getData();
                    ImageView v = (ImageView) findViewById(R.id.ivArrow4);
                    v.setImageDrawable(getResources().getDrawable(R.drawable.rightarrow));
                }else{
                    Log.d("Leave", "Error: Event not found.");
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", "Event Leave failed");

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

                    number_of_participants++;
                    participantNumber.setText(number_of_participants + " people are going");


                    ImageView v = (ImageView) findViewById(R.id.ivArrow4);
                    v.setImageDrawable(getResources().getDrawable(R.drawable.leftarrow));
                }else{
                    Log.d("Join", "Error: Event not found.");
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", "Event Join failed");

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
                    participants = response.getEvent_participants();
                    Log.d("event participants: " , participants.toString());
                    number_of_participants = participants.size();
                    participantNumber.setText(number_of_participants+" people are going");
                    host = response.getEvent_host();
                    eventPosts = e.getEvent_posts();

                    title= response.getEvent_title();
                    Long  date_as_long = Long.parseLong(response.getDate());
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                    Log.d("long",response.getDate() + "  " +  date_as_long + "asd");
                    date = ft.format(new Date(date_as_long));
                    //TODO fix the date
//                    date = response.getDate();
                    location = response.getEvent_location();
                    event_description =response.getEvent_description();
                    photo = response.getEvent_photo();
                    id = response.getId();
                    tags_event = response.getTags();
                    writeToList();
                } else {
                    Log.d("Event", "Cannot obtain event details.");
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", error.toString());

            }
        };

            API.getEventDetails("getEventDetail", id, response, errorListener);
    }

    Drawable d;
    void writeToList() {


        toolBarLayout.setTitle(title);

    /*  if(photo!=null){
          Drawable d = new BitmapDrawable(getResources(), photo);
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
              img.setBackground(d);z
          }
      }*/

        description.setText(event_description);
        eventdate.setText(date);
        participantNumber.setText(number_of_participants+" people are going");

        event_host.setText(host.getName()+ " "+ host.getSurname());
        eventlocation.setText(location);


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

        String tagString = "";
        for(int i= 0; i<tags_event.size();i++){
            tagString += tags_event.get(i)+" ";
        }
        tags.setText(tagString).addLinks(links).build();
        if(eventPosts.size()<=1){
            tComment.setText(eventPosts.size()+" Post");
        }else{
            tComment.setText(eventPosts.size()+" Posts");
        }

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
    public void onRestart() {
        super.onRestart();
        getData();

    }

}