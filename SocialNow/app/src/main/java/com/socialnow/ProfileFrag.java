package com.socialnow;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.parse.ParseUser;


public class ProfileFrag extends Fragment {
    Button logout;
    ImageView pp_imageView;
    private static final int SELECTED_PICTURE = 1;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v =  inflater.inflate(R.layout.frag_profile,container,false);
        pp_imageView = (ImageView) v.findViewById(R.id.profilepicture);

        pp_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO make it connected to the edit event page
                Intent i2 = new Intent(getActivity(), ProfilePage.class);
                startActivity(i2);
            }
        });
        return v;
    }




}