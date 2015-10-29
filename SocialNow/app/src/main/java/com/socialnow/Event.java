package com.socialnow;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mugekurtipek on 29/10/15.
 */
public class Event extends Activity {
    ListView listView;
    List<String> title;
    List<Date> date;
    List<Bitmap> photo;
    int maxCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        listView = (ListView) findViewById(R.id.listView);
        title = new LinkedList<String>();
        date = new LinkedList<Date>();
        photo = new LinkedList<Bitmap>();
        getData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                //TODO go to detail of the event
                Log.d("List check","Show me datil of the event");
            }
        });
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
        listView.setAdapter(new CustomListAdapter(this,title,date,photo));
        listView.setDivider(null);
    }

}
