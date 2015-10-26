package com.socialnow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.Parse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "nYZwu2d21NWTk7oIdS6N4IBcEDuNwoN48sRrl7Zd", "oRp46EvVPamz2XQtywDTImZI4YIPRLLlxVit8HcA");
        Intent i2 = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i2);
    }
}
