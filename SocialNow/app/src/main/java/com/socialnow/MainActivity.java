package com.socialnow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "nYZwu2d21NWTk7oIdS6N4IBcEDuNwoN48sRrl7Zd", "oRp46EvVPamz2XQtywDTImZI4YIPRLLlxVit8HcA");

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent i2 = new Intent(getApplicationContext(), HomePage.class);
            startActivity(i2);
        } else {
            Intent i2 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i2);
        }
        
    }
}
