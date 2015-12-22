package com.socialnow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.socialnow.Models.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lauamy on 21/12/15.
 */
public class EventTabFrag extends Fragment {
    private View v;
    private ListView listView;
    List<Event> events;
    List<String> titles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_parti, container, false);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fAddComment);
        fab.hide();
        listView = (ListView)v.findViewById(R.id.lvParti);
        events = new LinkedList<>();
        titles = new LinkedList<>();

        listView.setAdapter(new MyAdapter(getActivity(), R.layout.item_event, events));

        return v;
    }
    class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //TODO: view setting

            return v;
        }
    }
}