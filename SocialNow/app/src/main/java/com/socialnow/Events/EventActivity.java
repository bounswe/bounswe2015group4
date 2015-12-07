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

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.socialnow.Models.Event;
import com.socialnow.Models.User;
import com.socialnow.PartiActivity;
import com.socialnow.R;
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
    String parti;
    Event e;
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


        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.Parti);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewParti = new Intent(getApplicationContext(), PartiActivity.class).putExtra("from", "EventActivity");
                startActivity(viewParti);
            }
        });

        RelativeLayout viewComment=(RelativeLayout)findViewById(R.id.Comment);
        viewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent viewParti = new Intent(getApplicationContext(), PartiActivity.class);
                //startActivity(viewParti);
                Intent i2 = new Intent(getApplicationContext(), PartiActivity.class).putExtra("from", "Comment");
                startActivity(i2);
            }
        });


        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            title= null;
        } else {
            title= extras.getString("title");
            date = extras.getString("date");
            location = extras.getString("location");
            hostName = extras.getString("hostname");
            descrip = extras.getString("description");
            photo = extras.getString("photo");
            //participants = extras.getString("participants");
            //data = extras.getByteArray("photo");
        }

        e = extras.getParcelable("Event");

        //getData();

        writeToList();

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


        /* byte[] data;
        title = e.getTitle();
        date = e.getEvent_date();
        location = e.getEvent_location();
        descrip = e.getEvent_description();
        parti= e.getEvent_participants();
        photo = e.getEvent_photo();
        hostName = e.getEvent_host_token();*/
    }

  void writeToList(){


      toolBarLayout.setTitle(title);

    /*  if(photo!=null){
          Drawable d = new BitmapDrawable(getResources(), photo);
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
              img.setBackground(d);
          }
      }*/

      description.setText(descrip);
      eventdate.setText(date);

     /* if(parti!=null){
          participantNumber.setText(parti.length()+" people are going");
      }*/

      eventlocation.setText(location);
      event_host.setText(hostName);
      Drawable d = new BitmapDrawable(getResources(), getBitmapFromURL(photo));
      img.setBackground(d);

    //  TextView eventhost = (TextView) findViewById(R.id.tHostName);
    //  eventhost.setText(hostName);
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

}