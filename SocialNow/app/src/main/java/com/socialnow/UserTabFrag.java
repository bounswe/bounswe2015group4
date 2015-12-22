package com.socialnow;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lauamy on 21/12/15.
 */
public class UserTabFrag extends Fragment {
    private View v;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_parti, container, false);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fAddComment);
        fab.hide();
        listView = (ListView)v.findViewById(R.id.lvParti);
        //TODO: set adapter


        return v;
    }


}