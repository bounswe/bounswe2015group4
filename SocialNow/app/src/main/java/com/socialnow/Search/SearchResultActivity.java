package com.socialnow.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.socialnow.HomePage;
import com.socialnow.Models.SearchResult;
import com.socialnow.R;

/**
 * Created by lauamy on 20/12/15.
 */
public class SearchResultActivity extends AppCompatActivity {
    TabLayout tabLayout;
    SearchResult searchResult;
    String keyword = "";
    String from = "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchResult = (SearchResult)getIntent().getSerializableExtra("searchResult");
        keyword = getIntent().getStringExtra("keyword");
        from = getIntent().getStringExtra("from");


        setContentView(R.layout.activity_searchresult);
        if(from.equals("search"))
            getSupportActionBar().setTitle("Search Results");
        else
            getSupportActionBar().setTitle("Recommendation Results");
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("USER (" + searchResult.getUsers().size() + ")"));
        tabLayout.addTab(tabLayout.newTab().setText("EVENT (" + searchResult.getEvents().size() + ")"));
        tabLayout.addTab(tabLayout.newTab().setText("GROUP (" + searchResult.getGroups().size() + ")"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final SearchPagerAdapter adapter = new SearchPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), searchResult, keyword);
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
    }

    @Override
    public void onBackPressed() {
        if(from.equals("recommend")){
            Log.d("back", "back");
            Intent i2 = new Intent(getApplicationContext(), HomePage.class);
            startActivity(i2);
            this.finish();
        }else {
            super.onBackPressed();
        }
    }
}
