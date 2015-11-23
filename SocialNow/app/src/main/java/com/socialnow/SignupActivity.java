package com.socialnow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;

import java.util.Collections;


import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    Spinner spinner;
    EditText name;
    EditText surname;
    EditText email;
    EditText user_name;
    EditText password;
    CheckBox terms_and_services;
    String faculty_position = "";
    Context context;
    String uname;
    String usurname;
    String uemail;
    String upassword;
    String TAG;

    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        context = this;

        name = (EditText) findViewById(R.id.etName);
        surname = (EditText) findViewById(R.id.etSurname);
        email = (EditText) findViewById(R.id.etbMail);
        user_name = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPW);
        terms_and_services = (CheckBox) findViewById(R.id.cbAccept);

        uname = user_name.getText().toString();
        usurname = surname.getText().toString();
        uemail = email.getText().toString();
        upassword = password.getText().toString();

        TAG = SignupActivity.class.getSimpleName();



        spinner = (Spinner) findViewById(R.id.sUserType);
        adapter = ArrayAdapter.createFromResource(this, R.array.user_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    faculty_position = (String) parent.getItemAtPosition(position);
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                faculty_position = "";
            }
        });
    }

    public void send_to_approval(View v) {
        boolean[] input_check = new boolean[]{checkName(name.getText()), checkName(surname.getText()), checkMail(email.getText()), checkName(user_name.getText()), checkPassword(password.getText()), terms_and_services.isChecked(), faculty_position != ""};
        boolean is_inputs_right = true;
        for (int i = 0; i < input_check.length; i++) {
            if (!input_check[i]) {
                is_inputs_right = false;
                break;
            }
        }

        if (is_inputs_right) {
            /*
            ParseUser user = new ParseUser();
            user.setUsername(user_name.getText().toString());
            user.setPassword(password.getText().toString());
            user.setEmail(email.getText().toString());

// other fields can be set just like with ParseObject
            user.put("Name", name.getText().toString());
            user.put("Surname", surname.getText().toString());
            user.put("Department_Position", faculty_position);

            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // Hooray! Let them use the app now.
                        Toast.makeText(getBaseContext(), "Your request is sent", Toast.LENGTH_LONG).show();
                        Log.d("sign up", "User is created");
                        Intent i2 = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(i2);
                    } else {
                        Log.d("sign up error", "Something gone wrong whle sign up");
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
                        dlgAlert.setMessage("Something gone wrong!" + e.toString());
                        dlgAlert.setTitle("Error Message");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        dlgAlert.create().show();
                    }
                }
            }); */
            try {
                sendJson(10000, uname, usurname, uemail, upassword, faculty_position);
            } catch (IOException ex) {

            }

        } else {
            Toast.makeText(getBaseContext(), "Wrong Info", Toast.LENGTH_LONG).show();
        }
    }

    public String sendJson(int timeout, final String uname, final String usurname,final String uemail, final String upassword, final String faculty_position) throws IOException {
        HttpURLConnection c = null;
        StringBuilder sb =new StringBuilder();
        try {
            URL u = new URL(AppConfig.URL_REGISTER);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("POST");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", uemail);
            jsonObject.put("password", upassword);
            jsonObject.put("role", faculty_position);
            jsonObject.put("name", uname);
            jsonObject.put("surname", usurname);

            String json = jsonObject.toString();

            int HttpResult =c.getResponseCode();
            if(HttpResult ==HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        c.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                System.out.println(""+sb.toString());

                // Send POST output.
                DataOutputStream printout;
                printout = new DataOutputStream(c.getOutputStream ());
                printout.writeBytes(URLEncoder.encode(jsonObject.toString(), "UTF-8"));
                printout.flush();
                printout.close();

            }else{
                System.out.println(c.getResponseMessage());
            }

        } catch (MalformedURLException ex) {

        } catch (IOException ex) {

        }  catch (JSONException ex) {

        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {

                }
            }
        }
        return null;
    }

    private boolean checkPassword(Editable text) {
        if (text.toString() != "")
            return true;
        else
            return false;
    }

    private boolean checkMail(Editable text) {
        if (text.toString() != "")
            return true;
        else
            return false;
    }

    private boolean checkName(Editable text) {
        if (text.toString() != "")
            return true;
        else
            return false;
    }

}