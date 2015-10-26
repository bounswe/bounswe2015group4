package com.socialnow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

/**
 * Created by mugekurtipek on 26/10/15.
 */
public class ForgetPassword extends Activity implements View.OnClickListener {
    EditText etEmail;
    Button reset;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.forgetpassword);
        etEmail = (EditText) findViewById(R.id.email);
        reset = (Button) findViewById(R.id.btnReset);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnReset:
                ParseUser.requestPasswordResetInBackground(etEmail.getText().toString(), new RequestPasswordResetCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // An email was successfully sent with reset instructions.
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
                            dlgAlert.setMessage("Password reset instructions sent to your email.");
                            dlgAlert.setTitle("Successful!");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                            startActivity(i);
                                        }
                                    });
                            dlgAlert.create().show();
                        } else {
                            // Something went wrong. Look at the ParseException to see what's up.
                            Log.d("Error", "Email is not exist");
                            // An email was successfully sent with reset instructions.
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
                            dlgAlert.setMessage("Email is not exist.");
                            dlgAlert.setTitle("Error");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.create().show();
                        }
                    }
                });
                break;


            default:
                break;
        }
    }
}
