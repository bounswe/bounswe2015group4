package com.socialnow;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by lauamy on 29/10/15.
 */
public class PartiActivity extends AppCompatActivity {
    ListView listView;
    ListAdapter mAdapter;
    //RelativeLayout itemLayout;


    //TODO this activity should list the user's friends and should be able to give results for other activities.
    //Dummy Parti list with Profile Pics
    int [] mImgArr={R.drawable.host,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic,R.drawable.profilpic};
    String[] tvParti={"User 1","User 2","User 3","User 4","User 5","User 6","User 7"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parti);
        listView=(ListView)findViewById(R.id.lvParti);

        // Logic to check the calling activity
        String callingActivity = getIntent().getStringExtra("from");
        switch (callingActivity) {
            case "GroupActivity":
                mAdapter = new MemberAdapter(this,R.layout.item_member,tvParti);
                break;

            case "EditEventActivity":
                mAdapter = new GuestAdapter(this,R.layout.item_inviteguest,tvParti);
                break;

            case "EventActivity":
                mAdapter = new PartiAdapter(this,R.layout.item_parti,tvParti);
                break;

            case "EditGroupActivity":
                mAdapter = new EditMemberAdapter(this,R.layout.item_editmember,tvParti);
                listView.setAdapter(mAdapter);

                break;

            default:
                break;
        }
        listView.setAdapter(mAdapter);



        //Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

    }

    class PartiAdapter extends ArrayAdapter<String> {
        public PartiAdapter(Context context, int resource, String[] tvParti) {
            super(context, R.layout.item_parti, tvParti);
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_parti, parent, false);
            String item = getItem(position);

            TextView mText = (TextView) customView.findViewById(R.id.tvParti);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivParti);
            mText.setText(item);
            mImg.setImageResource(mImgArr[position]);
            return customView;


        }

    }
    class GuestAdapter extends ArrayAdapter<String> {
        public GuestAdapter(Context context, int resource, String[] tvParti) {
            super(context, R.layout.item_inviteguest, tvParti);
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_inviteguest, parent, false);
            String item = getItem(position);

            TextView mText = (TextView) customView.findViewById(R.id.tvGuest);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivGuest);
            CheckBox mCB = (CheckBox) customView.findViewById(R.id.cGuestInvite);
            mText.setText(item);
            mImg.setImageResource(mImgArr[position]);
            return customView;

        }

    }

    class MemberAdapter extends ArrayAdapter<String> {
        public MemberAdapter(Context context, int resource, String[] tvParti) {
            super(context, R.layout.item_inviteguest, tvParti);
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_member, parent, false);
            String item = getItem(position);

            TextView mText = (TextView) customView.findViewById(R.id.tvMember);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivMember);
            TextView mRole = (TextView) customView.findViewById(R.id.tvRole);
            mText.setText(item);
            mImg.setImageResource(mImgArr[position]);
            return customView;

        }

    }

    class EditMemberAdapter extends ArrayAdapter<String> {
        public EditMemberAdapter(Context context, int resource, String[] tvParti) {
            super(context, R.layout.item_editmember, tvParti);
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View customView = mInflater.inflate(R.layout.item_editmember, parent, false);
            String item = getItem(position);

            TextView mText = (TextView) customView.findViewById(R.id.tvMember);
            ImageView mImg = (ImageView) customView.findViewById(R.id.ivMember);
            CheckBox mCB = (CheckBox) customView.findViewById(R.id.cAddMember);
            mText.setText(item);
            mImg.setImageResource(mImgArr[position]);
            ToggleButton toggle=(ToggleButton)findViewById(R.id.tbModerator);

            return customView;

        }

    }



}
