package com.socialnow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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
import android.support.v7.widget.Toolbar;


import com.socialnow.HomeScreen.EventFrag;
import com.socialnow.HomeScreen.GroupFrag;
import com.socialnow.HomeScreen.HomeFrag;
import com.socialnow.HomeScreen.NotiFrag;
import com.socialnow.HomeScreen.ProfileFrag;
import com.socialnow.HomeScreen.SearchFrag;
import com.socialnow.Users.LoginActivity;
import com.socialnow.Users.Utils;


import java.io.IOException;

import static com.socialnow.R.string.drawer_open;

public class HomePage extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Button logout;
    private DrawerLayout drawerLayout;
    private ListView listview;
    private String[] panel;
    //Will be changed later depending the menu items we would like to include
//
    int [] img ={R.drawable.homedrawer,R.drawable.profilpic,R.drawable.eventdrawer,R.drawable.groupdrawer,R.drawable.notidrawer,R.drawable.searchdrawer,R.drawable.exit};
    private ActionBarDrawerToggle drawerListener;
    Fragment fragment;
    static Menu menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout =(DrawerLayout)findViewById(R.id.drawerLayout);
        panel=getResources().getStringArray(R.array.panel);
        listview =(ListView)findViewById(R.id.drawerList);
        //listview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, panel));
        ListAdapter mAdapter = new MyAdapter(this,R.layout.item_drawer,panel);
        listview.setAdapter(mAdapter);



        listview.setOnItemClickListener(this);
        //drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
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



        // Set default fragment to homepage
        fragment = new HomeFrag();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainContent,fragment).commit();
        setTitle(panel[0]);

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);

        return true;
    }*/


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
            if (position == 6){
                mText.setTextColor(Color.RED);
            }
            mImg.setImageResource(img[position]);
            return customView;

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

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
    protected void onPostCreate (Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 6) {
            if (Utils.getCurrentUserMode() || Utils.getCurrentUser() != null) {
                try {
                    Utils.logout();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent i2 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i2);
                finish();
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
                    Utils.updateProfile();
                    fragment = new ProfileFrag();
                    //changeToolBar();
                    break;

                case 2:
                    fragment = new EventFrag();

                    break;

                case 3:
                    fragment = new GroupFrag();
                    break;

                case 4:
                    fragment = new NotiFrag();
                    break;

               case 5:
                    fragment = new SearchFrag();
                    break;

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

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }





    public static void hideItem(int index)
    {
        MenuItem mi = menu.getItem(index);
        mi.setVisible(false);
    }

    public static void showItem(int index)
    {
        MenuItem mi = menu.getItem(index);
        mi.setVisible(true);
    }



}
