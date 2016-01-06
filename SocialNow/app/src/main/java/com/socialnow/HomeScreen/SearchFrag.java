package com.socialnow.HomeScreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.Models.SearchResult;
import com.socialnow.R;
import com.socialnow.Search.SearchResultActivity;

/**
 * Created by lauamy on 4/12/15.
 */
public class SearchFrag extends Fragment {
    private View v;
    Button bReset;
    Button bSearch;
    EditText etKeyword;
    ProgressDialog progress;
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

        bSearch = (Button) v.findViewById(R.id.bSearch);
        bSearch.setOnClickListener(new Button.OnClickListener() {

            @Override

            public void onClick(View v) {
                if(!etKeyword.getText().toString().equals("")) {
                    progress = ProgressDialog.show(getContext(), "",
                            "", true);
                    Response.Listener<SearchResult> response = new Response.Listener<SearchResult>() {
                        @Override
                        public void onResponse(SearchResult response) {
                            if (response != null) {
//                                Log.d("searchh", response.getGroups().size() + " " + response.getEvents().size() + " " + response.getUsers().size());
//                                for (int i = 0; i < response.getEvents().length; i++)
//                                    Log.d("Events", response.getEvents()[i].getEvent_description());
//                                for (int i = 0; i < response.getGroups().length; i++)
//                                    Log.d("Groups", response.getGroups()[i].getGroup_description());
//                                for (int i = 0; i < response.getUsers().length; i++)
//                                    Log.d("Users", response.getUsers()[i].getName());
                                progress.dismiss();
                                Intent i = new Intent(getActivity(), SearchResultActivity.class);
                                i.putExtra("searchResult", response);
                                i.putExtra("keyword", etKeyword.getText().toString());
                                i.putExtra("from", "search");
                                startActivity(i);
                                //getActivity().finish();
                            } else {
                                progress.dismiss();
                                Log.d("Event", "error");
                            }

                        }

                    };

                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Failed", error.toString());

                        }
                    };

                    API.search("search", etKeyword.getText().toString(), response, errorListener);

                }
            }

        });
        return v;

    }

}