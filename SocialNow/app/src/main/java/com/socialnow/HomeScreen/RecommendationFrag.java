package com.socialnow.HomeScreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.Models.SearchResult;
import com.socialnow.R;
import com.socialnow.Search.SearchResultActivity;


public class RecommendationFrag extends Fragment {
    View v;
    ProgressDialog progress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_recommendation, container, false);
        progress = ProgressDialog.show(getActivity(), "",
                "", true);
        Response.Listener<SearchResult> response = new Response.Listener<SearchResult>() {
            @Override
            public void onResponse(SearchResult response) {
                if (response != null) {
                    Log.d("recommend", response.getGroups().size() + " " + response.getEvents().size() + " " + response.getUsers().size());
                    progress.dismiss();
                    Intent i = new Intent(getActivity(), SearchResultActivity.class);
                    i.putExtra("searchResult", response);
                    i.putExtra("from", "recommend");
                    startActivity(i);
                    getActivity().finish();
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
                progress.dismiss();
            }
        };

        API.recommend("recommend", response, errorListener);



        return v;
    }


}
