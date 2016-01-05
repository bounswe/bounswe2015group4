package com.socialnow.Search;

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
import com.socialnow.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lauamy on 21/12/15.
 */
public class GroupTabFrag extends Fragment {
    private View v;
    private ListView listView;
    List<Group> groups;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_parti, container, false);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fAddComment);
        fab.hide();
        listView = (ListView)v.findViewById(R.id.lvParti);
        groups = new LinkedList<>();

        listView.setAdapter(new MyAdapter(getActivity(), R.layout.item_event, groups));

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