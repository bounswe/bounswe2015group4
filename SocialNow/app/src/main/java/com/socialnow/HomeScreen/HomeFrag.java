package com.socialnow.HomeScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.socialnow.R;


/**
 * Created by lauamy on 27/10/15.
 */
public class HomeFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //HomePage.hideItem(0);
        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.frag_home, container, false);
    }




}
