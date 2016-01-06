package com.socialnow.Search;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.Models.SearchResult;

/**
 * Created by mertcan on 6.1.2016.
 */
public class SearchUtil {
    public static ProgressDialog progress;
    public static void searchAnOpenActivity(final String keyword, final Activity context){
        progress = ProgressDialog.show(context, "",
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
                    Intent i = new Intent(context, SearchResultActivity.class);
                    i.putExtra("searchResult", response);
                    i.putExtra("keyword", keyword);
                    i.putExtra("from", "search");
                    context.startActivity(i);
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
                progress.dismiss();
            }
        };

        API.search("search", keyword, response, errorListener);
    }
}
