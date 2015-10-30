package com.socialnow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class EventActivity extends AppCompatActivity {
    ListView listView;

    //Dummy Comment List
    int [] ivParti={R.drawable.host,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic};
    String[] tvParti={"User 1","User 2","User 3","User 4","User 5","User 6","User 7"};
    String[] tvComment={"Let me comment on this event.",
            "Let me comment on this event.", "Let me comment on this event.",
            "Let me comment on this event.","Let me comment on this event.",
            "Let me comment on this event.","Let me comment on this event."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("Title");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO make it to be a floating action menu for users to share event, make comments and so on......
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.Parti);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewParti = new Intent(getApplicationContext(), PartiActivity.class);
                startActivity(viewParti);
            }
        });

        listView=(ListView)findViewById(R.id.lvComment);
        ListAdapter mAdapter = new MyAdapter(this,R.layout.item_comment,tvParti);
        listView.setAdapter(mAdapter);

    }
    class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter(Context context, int resource, String[] tvParti) {
            super(context, R.layout.item_comment, tvParti);
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_comment, parent, false);

            TextView mText = (TextView) customView.findViewById(R.id.tvParti);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivAuthor);
            TextView mComment = (TextView) customView.findViewById(R.id.tvComment);
            String item = getItem(position);
            mText.setText(item);
            mImg.setImageResource(ivParti[position]);
            mComment.setText(tvComment[position]);
            return customView;

        }

    }
}