package com.socialnow;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class ProfileFrag extends Fragment {

    private static final int SELECTED_PICTURE = 1;

    Button logout;
    ParseUser current_user;
    String user_id;
    ImageView profile_picture;
    TextView user_name;
    TextView user_email;

    String userName;
    String userEmail;
    Bitmap photo;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v =  inflater.inflate(R.layout.frag_profile,container,false);
        profile_picture = (ImageView) v.findViewById(R.id.profilepicture);
        user_name = (TextView) v.findViewById(R.id.tUserName);
        user_email = (TextView) v.findViewById(R.id.tUserEmail);
        current_user = ParseUser.getCurrentUser();
        user_id= current_user.getObjectId();
        userName = current_user.getString("username");
        userEmail = current_user.getString("email");

        user_name.setText(userName);
        user_email.setText(userEmail);

        //below is required when setting profile picture.
        //Intent i2 = new Intent(getActivity(), ProfilePage.class);
        //startActivity(i2);

        getData();

        return v;
    }


    void getData() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("username", userName);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, com.parse.ParseException e) {
                if (object == null) {
                    Log.d("score", "The getFirst request failed.");
                } else {
                    ParseFile fileObject;
                    byte[] data;
                    Log.d("score", "Retrieved the object.");

                    //hostName = object.getParseObject("event_host").getString("Name") + " " + object.getParseObject("event_host").getString("Surname");
                    fileObject = (ParseFile) object.getParseFile("Profile_Picture");
                    if (fileObject != null) {
                        try {
                            data = fileObject.getData();
                            Bitmap bMap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            photo = bMap;

                            writeToList();

                        } catch (com.parse.ParseException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        Log.d("post", "error retriving posts");
                    }
                }
            }

        });

    }

    void writeToList(){

        Drawable d = new BitmapDrawable(getResources(), photo);

        profile_picture.setImageBitmap(photo);



    }
}