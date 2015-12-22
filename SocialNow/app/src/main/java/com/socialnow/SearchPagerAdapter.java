package com.socialnow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.socialnow.UserTabFrag;

/**
 * Created by lauamy on 22/12/15.
 */
public class SearchPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public SearchPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                UserTabFrag tab1 = new UserTabFrag ();
                return tab1;
            case 1:
                EventTabFrag tab2 = new EventTabFrag ();
                return tab2;
            case 2:
                GroupTabFrag tab3 = new GroupTabFrag ();
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
