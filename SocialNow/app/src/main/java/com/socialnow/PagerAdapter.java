package com.socialnow;

/**
 * Created by lauamy on 10/11/15.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.socialnow.Models.Profile;
import com.socialnow.Util.AboutTabFrag;
import com.socialnow.Util.ActivityTabFrag;
import com.socialnow.Util.PhotoTabFrag;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Profile profile;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, Profile profileToShow) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        profile = profileToShow;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AboutTabFrag tab1 = new AboutTabFrag();
                tab1.p = profile;
                return tab1;
            case 1:
                ActivityTabFrag tab2 = new ActivityTabFrag();
                tab2.p = profile;
                return tab2;
            case 2:
                PhotoTabFrag tab3 = new PhotoTabFrag();
                tab3.p = profile;
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