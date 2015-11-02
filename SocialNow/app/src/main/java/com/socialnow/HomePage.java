package com.socialnow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseUser;

import static com.socialnow.R.string.drawer_open;

public class HomePage extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Button logout;
    private DrawerLayout drawerLayout;
    private ListView listview;
    private String[] panel;
    //Will be changed later depending the menu items we would like to include
    int [] img ={R.drawable.host,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic};
    private ActionBarDrawerToggle drawerListener;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawerLayout =(DrawerLayout)findViewById(R.id.drawerLayout);
        panel=getResources().getStringArray(R.array.panel);
        listview =(ListView)findViewById(R.id.drawerList);
        //listview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, panel));
        ListAdapter mAdapter = new MyAdapter(this,R.layout.item_drawer,panel);
        listview.setAdapter(mAdapter);

        listview.setOnItemClickListener(this);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        drawerListener = new ActionBarDrawerToggle(this,drawerLayout,
                drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }


        };
        drawerLayout.setDrawerListener(drawerListener);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set default fragment to homepage
        fragment = new HomeFrag();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainContent,fragment).commit();
        setTitle(panel[0]);

    }
    class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter(Context context, int resource, String[] panel) {
            super(context, R.layout.item_drawer, panel);
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_drawer, parent, false);

            TextView mText = (TextView) customView.findViewById(R.id.tvDrawer);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivDrawer);
            String item = getItem(position);
            mText.setText(item);
            mImg.setImageResource(img[position]);
            return customView;

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (drawerListener.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        drawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate (Bundle savedInstanceState){

        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 5) {
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {
                ParseUser.logOut();
                Intent i2 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i2);
            } else {
                Log.d("error_logout", "logout failed");
            }
        }

        else {
            Fragment fragment;
            Activity activity;
            switch (position){
                case 0:
                    fragment = new HomeFrag();
                    break;

                case 1:
                    fragment = new ProfileFrag();
                    break;

                case 2:
                    fragment = new EventFrag();
                    break;

                case 3:
                    fragment = new GroupFrag();
                    break;

                case 4:
                    fragment = new NotiFrag();

                default:
                    fragment = new HomeFrag();
                    break;
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.mainContent,fragment).commit();
            drawerLayout.closeDrawers();
            selectItem(position);
        }
    }
    public void selectItem(int position)
    {
        listview.setItemChecked(position, true);
        setTitle(panel[position]);
    }

    public void setTitle(String title)
    {
        getSupportActionBar().setTitle(title);
    }



    public void log_out(View v){
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            ParseUser.logOut();
            Intent i2 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i2);
        } else {
            Log.d("error_logout", "logout failed");
        }
    }

}
