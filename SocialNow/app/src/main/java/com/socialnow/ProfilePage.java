package com.socialnow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import android.widget.ImageView;

//In this activity Profile Picture is set by picking image from gallery. Not completed.

public class ProfilePage extends AppCompatActivity {
    Button logout;
    ParseUser current_user = ParseUser.getCurrentUser();
    String user_id= current_user.getObjectId();
    ImageView profile_picture;
    TextView user_name;
    TextView user_email;

    private static final int SELECTED_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_profile);
        profile_picture = (ImageView)findViewById(R.id.profilepicture);

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,  SELECTED_PICTURE);
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

    public void onActivityResult(int reqCode, int resCode, Intent data)
    {
        super.onActivityResult(reqCode,resCode,data);

        if(resCode == RESULT_OK)
        {
            if(reqCode == 1)
            {

                Uri uri = data.getData();
                profile_picture.setImageURI(uri);

                //
                String[] projection = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                cursor.moveToFirst();

                int column_index = cursor.getColumnIndex(projection[0]);
                String file_path = cursor.getString(column_index);
                cursor.close();
                //

                File photo = new File(file_path);

                Bitmap selected_img = BitmapFactory.decodeFile(file_path);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                selected_img.compress(Bitmap.CompressFormat.PNG, 100, stream);
                ParseFile fileObject = new ParseFile("DocImage.jpg", stream.toByteArray());

                    fileObject.saveInBackground();
                    current_user.put("profile_picture", fileObject);
                    current_user.saveInBackground();

                /*Drawable d = new BitmapDrawable(your_selected_image);
                pp_imageView.setBackground(d);*/
                profile_picture.setImageBitmap(selected_img);


            }
        }
    }


}


