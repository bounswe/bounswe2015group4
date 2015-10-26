package com.socialnow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by lauamy on 23/10/15.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etUserName;
    EditText etPassword;
    Button btnLogin;
    Button btnSignup;
    Button btnForgotPassword;
    String userName;
    String password;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable Local Datastore.
        context = this;

        setContentView(R.layout.activity_login);
        etUserName = (EditText) findViewById(R.id.et_Username);
        etPassword = (EditText) findViewById(R.id.et_PW);
        btnLogin = (Button) findViewById(R.id.bLogin);
        btnLogin.setOnClickListener(this);
        btnSignup = (Button) findViewById(R.id.bSignUp);
        btnSignup.setOnClickListener(this);
        btnForgotPassword = (Button) findViewById(R.id.bFpw);
        btnForgotPassword.setOnClickListener(this);

    }
    void isValidUser(){
        ParseUser.logInInBackground(userName, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    //TODO create intent to home page
                    Log.d("Right credentials:", "Valid username and password");
                    Intent i2 = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(i2);
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    Log.d("Wrong credentials:", "Not valid username and password");
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
                    dlgAlert.setMessage("wrong password or username");
                    dlgAlert.setTitle("Error Message");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    etUserName.setText("");
                                    etPassword.setText("");
                                }
                            });
                    dlgAlert.create().show();
                }

                }

        });
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bLogin:
                userName = etUserName.getText().toString();
                password = etPassword.getText().toString();
                isValidUser();
                break;

            case R.id.bSignUp:
                Intent i2 = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i2);
                break;

            case R.id.bFpw:
                Intent i = new Intent(getApplicationContext(), ForgetPassword.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
