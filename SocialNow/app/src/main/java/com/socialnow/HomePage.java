package com.socialnow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class HomePage extends AppCompatActivity {
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void log_out(View v){
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            ParseUser.logOut();
            Intent i2 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i2);
        } else {
            Log.d("error_logout", "logout failed");
        }
    }

}
