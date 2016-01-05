package com.socialnow.Users;

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


import com.socialnow.R;

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

                break;


            default:
                break;
        }
    }
}
