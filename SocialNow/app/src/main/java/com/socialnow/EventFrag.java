package com.socialnow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lauamy on 27/10/15.
 */
public class EventFrag extends Fragment {
    private ListView listView;
    private View v;
    List<String> title;
    List<Date> date;
    List<Bitmap> photo;
    int maxCount = 0;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        v = inflater.inflate(R.layout.frag_event, container, false);
        listView = (ListView)v.findViewById(R.id.lvEvent);
        title = new LinkedList<String>();
        date = new LinkedList<Date>();
        photo = new LinkedList<Bitmap>();
        getData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO go to detail of the event
                Log.d("List check", "Show me datil of the event");
            }

        });

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO make it connected to the edit event page
            }
        });

        return v;


    }

    class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }
// Push
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_event,null);
            TextView eventname = (TextView) v.findViewById(R.id.tEname);
            eventname.setText(title.get(position));
            ImageView img = (ImageView) v.findViewById(R.id.ivEvent);
            img.setImageBitmap(photo.get(position));
            TextView eventdate = (TextView) v.findViewById(R.id.tEdate);
            eventdate.setText(date.get(position).toString());
            //TODO add location and host name
            TextView eventlocation = (TextView) v.findViewById(R.id.tElocation);
            eventlocation.setText("Lol");
            TextView eventhost = (TextView) v.findViewById(R.id.tHostName);
            eventhost.setText("Lol");


            return v;
        }
    }

    void getData() {
        ParseQuery query = new ParseQuery("Event");
        query.findInBackground(new FindCallback() {
            @Override
            public void done(List objects, com.parse.ParseException e) {
            }

            @Override
            public void done(Object o, Throwable throwable) {
                List<ParseObject> myObject = (List<ParseObject>) o;
                maxCount = myObject.size();
                if (throwable == null && maxCount > 0) {
                    int count = 0;
                    ParseFile fileObject;
                    byte[] data;
                    for (int i = 0; i < maxCount; i++) {
                        title.add(count, myObject.get(i).getString("title"));
                        date.add(count, myObject.get(i).getDate("event_date"));
                        fileObject = (ParseFile) myObject.get(i).getParseFile("event_photo");
                        if (fileObject != null) {
                            try {
                                data = fileObject.getData();
                                Bitmap bMap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                photo.add(count, bMap);
                                count++;
                                if (count == maxCount) {
                                    //All friends added, do something
                                    writeToList();
                                }
                            } catch (com.parse.ParseException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                } else {
                    Log.d("post", "error retriving posts");
                }
            }
        });
    }

    void writeToList(){
        listView.setAdapter(new MyAdapter(getActivity(), R.layout.item_event, title));
    }
}

