package com.socialnow.Events;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

//import com.parse.ParseFile;
//import com.parse.ParseObject;
//import com.parse.ParseUser;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.apradanas.simplelinkabletext.Link;
import com.apradanas.simplelinkabletext.LinkableEditText;
import com.socialnow.API.API;
import com.socialnow.Models.Event;
import com.socialnow.Models.Event_Detail;
import com.socialnow.PartiActivity;
import com.socialnow.R;
import com.socialnow.Users.Utils;
import com.android.volley.Response.Listener;

/**
 * Created by lauamy on 2/11/15.
 */
//TODO Event Image Adding option, Invite Guest Option, Privacy Option, Invite problems, End time
public class EditEventActivity extends AppCompatActivity{
    TextView tvDate, tvSTime, tvETime, etEventName, etEventDes, etEventLoca, etPhoto;
    com.apradanas.simplelinkabletext.LinkableEditText tvTags;
    private Button btDate, btSTime,btETime;
    private int mYear, mMonth, mDay, mSHour, mSMinute, mEHour, mEMinute;
    String mTitle="Create Event";
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String privacy_option = "";
    Context context;
    String eTitle1 = "Invalid Date";
    String eTitle2 = "Invalid Time";
    String eTitle3 = "Invalid Date/Time";
    String eMsg1 = "Past date is selected";
    String eMsg2 = "End time is not later than Start Time";
    String eMsg3 = "Past time/date cannot be inputted";

    final Calendar c = Calendar.getInstance();
    int mCHour = c.get(Calendar.HOUR_OF_DAY);
    int mCMinute = c.get(Calendar.MINUTE);
    int CurrentTime = mCHour*60+mCMinute;


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
        context = this;



        getSupportActionBar().setTitle(mTitle);

        etEventName = (TextView) findViewById(R.id.etEventName);
        etEventDes = (TextView) findViewById(R.id.etEventDes);
        etEventLoca = (TextView) findViewById(R.id.etEventLoca);
        etPhoto = (EditText) findViewById(R.id.etPhoto);
        tvTags = (com.apradanas.simplelinkabletext.LinkableEditText) findViewById(R.id.tags);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvSTime = (TextView) findViewById(R.id.tvSTime);
        tvETime = (TextView) findViewById(R.id.tvETime);

        // etEventName.setText("first");
        // etEventDes.setText("desc");
        // etEventLoca.setText("loca");
        /*if (!editFlag){
            tvDate.setText("2015-11-3");
            tvSTime.setText("20:45");
            tvETime.setText("21:45");
        }*/
        tvSTime.setText("");
        tvETime.setText("");

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
        adapter = ArrayAdapter.createFromResource(this, R.array.event_privacy, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.Guest);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewGuest = new Intent(getApplicationContext(), PartiActivity.class).putExtra("from", "EditEventActivity");
                startActivity(viewGuest);
                //TODO activity should be started to get results as the guests to be invited.
            }
        });

        Link linkHashtag = new Link(Pattern.compile("(\\w+)"))
                .setUnderlined(true)
                .setTextStyle(Link.TextStyle.BOLD)
                .setClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String text) {
                        Toast.makeText(EditEventActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                });
        List<Link> links = new ArrayList<>();
        links.add(linkHashtag);

        tvTags.addLinks(links);
    }

    public void create_event(View v){
        if(inputs_correct()){
            Event event = new Event();
            event.setTitle(etEventName.getText().toString());
            event.setEvent_description(etEventDes.getText().toString());
            event.setEvent_location(etEventLoca.getText().toString());
            //TODO privacy setting should be added
            event.set_visibleTo("all");
            //TODO add the end date
            event.setEvent_date(tvDate.getText().toString() + " " + tvSTime.getText().toString());
            event.setEvent_photo(etPhoto.getText().toString());
            String event_tags = tvTags.getText().toString();
            String tagArray[] = event_tags.split("\\s+");
            String tagForDb = "";
            for(int i = 0;i<tagArray.length;i++){
                tagForDb += tagArray[i] + ",";
            }
            event.setEvent_tags(tagForDb);
            // event.setEvent_date(getEventDate().getTime());
//            event.setEvent_photo(getEventPhoto());
            event.setEvent_host_token(Utils.getCurrentUser().getUser_token());
            //TODO guest member adding should be handled
            //event.put("event_members", )
            //TODO comment part should also be handled
            //event.put("event_comments", )

            Response.Listener<Event> response = new Response.Listener<Event>() {
                @Override
                public void onResponse(Event response) {
                    if(response.getId() != -1) {
                        Log.d("Event", "Creating success " + response.getEvent_description());

                    }else{
                        Log.d("Creation", "Error: Unknown");
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Failed", "Creation Failed");

                }
            };
            API.createEvent("createEvent", event, response, errorListener);

            Toast.makeText(getBaseContext(), "Doing", Toast.LENGTH_LONG).show();


        }else
        {
            if (PastTimeInput())
            {
                showErrorDialog(3,3);
            }
            Toast.makeText(getBaseContext(), "Wrong Info", Toast.LENGTH_LONG).show();
        }
    }

    private boolean inputs_correct() {
        if(etEventName.getText().toString() != "" && etEventDes.getText().toString() != "" && etEventLoca.getText().toString() != "" &&
                tvDate.getText().toString() != "" && etEventName.getText().toString() != "" && etEventName.getText().toString() != "")
        {
            if (PastTimeInput()){
                return false;
            }
        }
        return true;
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
                        // Check if the date is in the past

                        // Show selected date in text field
                        tvDate.setText(year + "/" + (monthOfYear + 1) + "/"
                                + dayOfMonth);

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
                        if (validStartTime(hourOfDay, minute)){
                            tvSTime.setText(addZero(hourOfDay) + ":" + addZero(minute));
                        }
                        else
                            showErrorDialog(2,2);

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
                        if (validEndTime(hourOfDay, minute)){
                            tvETime.setText(addZero(hourOfDay) + ":" + addZero(minute));
                        }
                        else{
                            showErrorDialog(2,2);
                        }
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
    boolean validStartTime(int mHour,int mMinute){
        int StartTime = mHour*60+mMinute;
        int EndTime = mEHour*60+mEMinute;
        String mString = (String) tvETime.getText().toString();
        if (!mString.equals("")) {
            if (StartTime >= EndTime)
                return false;
        }
        return true;
    }

    Boolean validEndTime(int mHour,int mMinute){
        int StartTime = mSHour*60+mSMinute;
        int EndTime = mHour*60+mMinute;
        String mString = (String) tvSTime.getText().toString();
        if (!mString.equals("")) {
            if (StartTime >= EndTime)
                return false;
        }
        return true;
    }

    public Date getEventDate() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        ft.setTimeZone(TimeZone.getDefault());

        String input = tvDate.getText().toString() + "T" + tvSTime.getText().toString() + ":00";

        System.out.println(input);

        Date t = new Date();

        try {
            t = ft.parse(input);
            System.out.println(t);
        } catch (ParseException e) {
            System.out.println("Unparseable using " + ft);
        }
        return t;
    }

    public void getEventPhoto() {
    }

    void showErrorDialog(int mTitle, int mMsg){
        // 1: Date; 2:Time
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
        switch (mTitle){
            case 1:
                dlgAlert.setTitle(eTitle1);
                break;
            case 2:
                dlgAlert.setTitle(eTitle2);
                break;
            case 3:
                dlgAlert.setTitle(eTitle3);
            default:
                break;
        }

        switch (mMsg){
            case 1:
                dlgAlert.setMessage(eMsg1);
                break;
            case 2:
                dlgAlert.setMessage(eMsg2);
                break;
            case 3:
                dlgAlert.setMessage(eMsg3);
            default:
                break;

        }
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
    boolean PastTimeInput(){
        String mString = (String) tvSTime.getText().toString();
        char[] mCharArr = mString.toCharArray();
        int StartTime = (mCharArr[0]*10+mCharArr[1])*60 +(mCharArr[3]*10 + mCharArr[4]);
        if (StartTime > CurrentTime)
            return false;
        else
            return true;
    }
}