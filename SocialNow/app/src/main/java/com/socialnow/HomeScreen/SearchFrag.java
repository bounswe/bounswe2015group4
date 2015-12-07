package com.socialnow.HomeScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.socialnow.R;

/**
 * Created by lauamy on 4/12/15.
 */
public class SearchFrag extends Fragment {
    private View v;
    Button bReset;
    EditText etKeyword;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = inflater.inflate(R.layout.frag_search,container,false);
        etKeyword = (EditText)v.findViewById(R.id.etKeyword);
        bReset = (Button) v.findViewById(R.id.bReset);
        bReset.setOnClickListener(new Button.OnClickListener() {

            @Override

            public void onClick(View v) {

                etKeyword.setText("");
            }

        });


        return v;

    }
}