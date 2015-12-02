package com.socialnow;

/**
 * Created by lauamy on 10/11/15.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.socialnow.Util.AboutTabFrag;
import com.socialnow.Util.ActivityTabFrag;
import com.socialnow.Util.PhotoTabFrag;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AboutTabFrag tab1 = new AboutTabFrag();
                return tab1;
            case 1:
                ActivityTabFrag tab2 = new ActivityTabFrag();
                return tab2;
            case 2:
                PhotoTabFrag tab3 = new PhotoTabFrag();
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