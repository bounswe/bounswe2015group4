package com.socialnow;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.ParseObject;

/**
 * Created by lauamy on 2/11/15.
 */
public class EditEventActivity extends AppCompatActivity{
    TextView tvDate, tvSTime, tvETime, etEventName, etEventDes, etEventLoca;
    private Button btDate, btSTime,btETime;
    private int mYear, mMonth, mDay, mSHour, mSMinute, mEHour, mEMinute;
    String mTitle="Create Event";
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String privacy_option = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editevent);
        Intent i = getIntent();

        Boolean editFlag = false;
        if(i != null && i.getStringExtra("type").equals("create")){
            editFlag = false;
        }else
            editFlag = true;


        if (editFlag){
            mTitle = "Edit Event";
        }


        getSupportActionBar().setTitle(mTitle);

        etEventName = (TextView) findViewById(R.id.etEventName);
        etEventDes = (TextView) findViewById(R.id.etEventDes);
        etEventLoca = (TextView) findViewById(R.id.etEventLoca);

        tvDate = (TextView) findViewById(R.id.tvDate);
        tvSTime = (TextView) findViewById(R.id.tvSTime);
        tvETime = (TextView) findViewById(R.id.tvETime);

        btDate = (Button) findViewById(R.id.btDate);
        btSTime = (Button) findViewById(R.id.btSTime);
        btETime = (Button) findViewById(R.id.btETime);

        btDate.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        btSTime.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                showStartTimePickerDialog();
            }
        });

        btETime.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                showEndTimePickerDialog();
            }
        });

        spinner = (Spinner) findViewById(R.id.sPrivacy);
        adapter = ArrayAdapter.createFromResource(this, R.array.privacy, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.Guest);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewGuest = new Intent(getApplicationContext(), PartiActivity.class);
                startActivity(viewGuest);
            }
        });

    }

    public void create_event(View v){
        if(inputs_correct()){
            ParseObject event = new ParseObject("Event");
            event.put("title", etEventName.getText().toString());
            event.put("event_description", etEventDes.getText().toString());
            event.put("event_location", etEventLoca.getText().toString());
            event.put("event_date", etEventLoca.getText().toString());
            event.put("event_location", etEventLoca.getText().toString());
            event.put("event_location", etEventLoca.getText().toString());
            event.saveInBackground();
        }else{
            Toast.makeText(getBaseContext(), "Wrong Info", Toast.LENGTH_LONG).show();
        }
    }

    private boolean inputs_correct() {
        if(etEventName.getText().toString() != "" && etEventDes.getText().toString() != "" && etEventLoca.getText().toString() != "" &&
        tvDate.getText().toString() != "" && etEventName.getText().toString() != "" && etEventName.getText().toString() != "")
            return true;
        else
            return false;
    }


    public void showDatePickerDialog() {
        //Initialize the default date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Show DatePicker
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Show selected date in text field
                        tvDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/"
                                + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    public void showStartTimePickerDialog() {
        // Initialize the default time
        final Calendar c = Calendar.getInstance();
        mSHour = c.get(Calendar.HOUR_OF_DAY);
        mSMinute = c.get(Calendar.MINUTE);

        // Show TimePicker
        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        // Show selected time in text field
                        /*while (!validStartTime(hourOfDay, minute)){
                            // TODO: alert user if endtime < starttime

                        }*/
                        tvSTime.setText(addZero(hourOfDay) + ":" + addZero(minute));

                    }
                }, mSHour, mSMinute, false);
        tpd.show();
    }

    public void showEndTimePickerDialog() {
        // Initialize the default time
        final Calendar c = Calendar.getInstance();
        mEHour = c.get(Calendar.HOUR_OF_DAY);
        mEMinute = c.get(Calendar.MINUTE);


        // Show TimePicker
        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        // Show selected time in text field
                        String mString = (String) tvSTime.getText().toString();
                        if (mString.equals(null))
                        {
                            System.out.println("X");
                        }
                        /*while (!validEndTime(hourOfDay,minute)){
                            // TODO: alert user if endtime < starttime

                        }*/
                        tvETime.setText(addZero(hourOfDay) + ":" + addZero(minute));
                    }
                }, mEHour, mEMinute, false);
        tpd.show();
    }

    String addZero(int mValue)
    {
        String mString = String.valueOf(mValue);
        if (mValue < 10){
            mString=( "0"+mValue);
        }
        return mString;
    }
    Boolean validStartTime(int mHour,int mMinute){
        int StartTime = mHour*60+mMinute;
        int EndTime = mEHour*60+mEMinute;
        if (EndTime > StartTime){
            return true;
        }
        else
            return false;
    }

    Boolean validEndTime(int mHour,int mMinute){
        int StartTime = mSHour*60+mSMinute;
        int EndTime = mHour*60+mMinute;
        if (EndTime > StartTime){
            return true;
        }
        else
            return false;
    }

}
