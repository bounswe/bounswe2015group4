package com.socialnow.Search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.socialnow.HomeScreen.EventFrag;
import com.socialnow.HomeScreen.GroupFrag;
import com.socialnow.Models.SearchResult;

/**
 * Created by lauamy on 22/12/15.
 */
public class SearchPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    SearchResult response;
    Bundle bundle = new Bundle();
    String keyword;

    public SearchPagerAdapter(FragmentManager fm, int NumOfTabs, SearchResult response, String keyword) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.response = response;
        this.keyword = keyword;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                UserTabFrag tab1 = new UserTabFrag ();
                bundle.putString("from", "Search");
                bundle.putString("keyword", keyword);
                bundle.putSerializable("searchUsers", response.getUsers());
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                EventFrag tab2 = new EventFrag ();
                bundle.putString("from", "Search");
                bundle.putString("keyword", keyword);
                bundle.putSerializable("searchEvents", response.getEvents());
                tab2.setArguments(bundle);
                return tab2;
            case 2:
                GroupFrag tab3 = new GroupFrag ();
                bundle.putString("from", "Search");
                bundle.putString("keyword", keyword);
                bundle.putSerializable("searchGroups", response.getGroups());
                tab3.setArguments(bundle);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
