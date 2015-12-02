package com.socialnow.Groups;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.socialnow.PartiActivity;
import com.socialnow.R;

/**
 * Created by lauamy on 20/11/15.
 */
public class EditGroupActivity extends AppCompatActivity{
    String mTitle="Create Group";
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editgroup);

        getSupportActionBar().setTitle(mTitle);

        spinner = (Spinner) findViewById(R.id.sPrivacy);
        adapter = ArrayAdapter.createFromResource(this, R.array.event_privacy, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.Member);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addMember = new Intent(getApplicationContext(), PartiActivity.class).putExtra("from", "EditGroupActivity");
                startActivity(addMember);
                //TODO activity should be started to get results as the guests to be invited.
            }
        });


    }
}
