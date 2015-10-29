package com.socialnow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lauamy on 27/10/15.
 */
public class EventFrag extends Fragment {
    private ListView listView;
    private View v;

    //Dummy event list
    int [] ivEvent={R.drawable.devent,R.drawable.devent,R.drawable.devent,R.drawable.devent};
    String[] eName={"Event 1", "Event 2", "Event 3", "Event 4"};
    String[] eDate={"DD/MM/2015", "DD/MM/2015", "DD/MM/2015", "DD/MM/2015"};
    String[] eLocation={"Istanbul", "Istanbul", "Istanbul", "Istanbul"};
    String[] eHost={"Abc", "Def", "Ghi", "Jkl"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        v = inflater.inflate(R.layout.frag_event, container, false);
        listView = (ListView)v.findViewById(R.id.lvEvent);

        listView.setAdapter(new MyAdapter(getActivity(), R.layout.item_event, eName));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), eName[position], Toast.LENGTH_SHORT).show();
            }

        });

        return v;


    }

    class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
        }
// Push
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_event,null);
            TextView eventname = (TextView) v.findViewById(R.id.tEname);
            eventname.setText(eName[position]);
            ImageView img = (ImageView) v.findViewById(R.id.ivEvent);
            img.setBackgroundResource(ivEvent[position]);
            TextView eventdate = (TextView) v.findViewById(R.id.tEdate);
            eventdate.setText(eDate[position]);
            TextView eventlocation = (TextView) v.findViewById(R.id.tElocation);
            eventlocation.setText(eLocation[position]);
            TextView eventhost = (TextView) v.findViewById(R.id.tHostName);
            eventhost.setText(eHost[position]);

            return v;
        }
    }
}

