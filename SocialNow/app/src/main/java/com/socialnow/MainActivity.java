package com.socialnow;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.Models.NotificationRetrieve;
import com.socialnow.Models.SearchResult;
import com.socialnow.Search.SearchResultActivity;
import com.socialnow.Users.LoginActivity;
import com.socialnow.Users.Utils;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor loginStateEditor;
    boolean done = false;
    public static ArrayList<NotificationRetrieve> notifications = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;

        Utils.initialize(this);


        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    while(true){
                        Log.d("asd",Utils.getCurrentUser().getUser_token());
                        done = false;
                        Response.Listener<NotificationRetrieve[]> response = new Response.Listener<NotificationRetrieve[]>() {
                            @Override
                            public void onResponse(NotificationRetrieve[] response) {
                                if (response != null) {
                                    for(int i = 0; i < response.length; i++) {
                                        notifications.add(response[i]);
                                        NotificationCompat.Builder mBuilder =
                                                new NotificationCompat.Builder(getApplicationContext())
                                                        .setSmallIcon(R.drawable.notidrawer)
                                                        .setContentTitle("You have a new notification from " + response[i].getFrom_user().getName() + " " + response[i].getFrom_user().getSurname())
                                                        .setContentText(response[i].getFrom_user().getName() + " " + response[i].getFrom_user().getSurname() + " wants you to join " + response[i].getEvent().getTitle());
                                        Log.d("asdasd", "You have a new notification from " + response[i].getFrom_user() + " " + response[i].getFrom_user() + " wants you to join " + response[i].getEvent().getTitle());
                                        int mNotificationId = notifications.size();
                                        NotificationManager mNotifyMgr =
                                                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                        mNotifyMgr.notify(mNotificationId, mBuilder.build());
                                    }
                                } else {
                                    Log.d("Event", "error");
                                }
                                done = true;
                            }

                        };

                        Response.ErrorListener errorListener = new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Failed", error.toString());
                                done = true;
                            }
                        };

                        API.getNotification("notification", response, errorListener);
                        while(!done);
                        Thread.sleep(10000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
//asd
        thread.start();

        if(Utils.getCurrentUserMode()){
            Utils.retrieveUser();
//            (Utils.getCurrentUser().getName() + " " + Utils.getCurrentUser().getPhoto());
            Intent i2 = new Intent(getApplicationContext(), HomePage.class);
            startActivity(i2);
        }
        else{
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }


       /* if (currentUser != null) {
            Intent i2 = new Intent(getApplicationContext(), HomePage.class);
            i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i2);
        } else {
            Intent i2 = new Intent(getApplicationContext(), LoginActivity.class);
            i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i2);
        }*/


        this.finish();

    }
}
