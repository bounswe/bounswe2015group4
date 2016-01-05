package com.socialnow.HomeScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.socialnow.HomePagerAdapter;
import com.socialnow.R;


/**
 * Created by lauamy on 27/10/15.
 */
public class HomeFrag extends Fragment {
    private View v;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //HomePage.hideItem(0);
        setHasOptionsMenu(false);
        v = inflater.inflate(R.layout.frag_home, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("MY EVENTS"));
        tabLayout.addTab(tabLayout.newTab().setText("MY GROUPS"));
        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager);
        final HomePagerAdapter adapter = new HomePagerAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }




}
