package com.socialnow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.socialnow.Users.LoginActivity;
import com.socialnow.Users.Utils;

import android.content.SharedPreferences;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor loginStateEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;

        Utils.initialize(this);


        if(Utils.getCurrentUserMode()){
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
