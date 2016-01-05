package com.socialnow.Users;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.API.API;
import com.socialnow.App;
import com.socialnow.Models.Profile;
import com.socialnow.Models.User;
import com.socialnow.Util.InternalStorage;

import java.io.IOException;

/**
 * Created by mertcan on 2.12.2015.
 */
public class Utils {
    private static final String PROFILE = "profile";
    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor loginStateEditor;
    private static User currentUser;
    public static String USER = "current_user";
    private static Profile currentProfile;

    public static void initialize(Context context)  {
        if(sharedPref == null || loginStateEditor == null) {
            sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
            loginStateEditor = sharedPref.edit();
        }
        if(getCurrentUserMode())
            retrieveUser();

    }

    public static User retrieveUser(){
        try {
            currentUser = (User) InternalStorage.readObject(App.getInstance(), USER);
            currentProfile = (Profile) InternalStorage.readObject(App.getInstance(), PROFILE);
            if(currentProfile == null)
                updateProfile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return currentUser;
    }

    public static void cacheUser(User u)  {
        try {
            InternalStorage.writeObject(App.getInstance(), USER, u);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void cacheProfile(Profile p)  {
        try {
            InternalStorage.writeObject(App.getInstance(), PROFILE, p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentProfile = p;
    }

    public static Profile retrieveProfile(){
        try {
            currentProfile = (Profile) InternalStorage.readObject(App.getInstance(), PROFILE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return currentProfile;
    }

    public static void setCurrentUser(boolean logged, User currentUser) {
        Utils.currentUser = currentUser;
        loginStateEditor.putBoolean("success_login", logged);
        loginStateEditor.commit();
    }

    public static boolean getCurrentUserMode() {
        return sharedPref.getBoolean("success_login", false);
    }
    public static User getCurrentUser(){
        return currentUser;
    }

    public static Profile getCurrentProfile(){
        if(currentProfile == null){
            updateProfile();
            while(currentProfile == null);
        }
        return currentProfile;
    }

    public static void logout() throws IOException {
        cacheUser(new User());
        setCurrentUser(false, null);
    }
    public static void  updateProfile() {
        Response.Listener<Profile> response = new Response.Listener<Profile>() {
            @Override
            public void onResponse(Profile response) {
                if (response.getName() != null) {
                    Log.d("Profile", "success " + response.getEmail() + " " + response.getName());

                    // Writing data to SharedPreferences
                    Utils.cacheProfile(response);
                    currentProfile = response;

                }else{
                    Log.d("Login", "Error: " + response.getUser_token());
                    Log.d("Wrong credentials:", "Not valid username and password");
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Failed", "Login Failed");

            }
        };
        API.profileInfo("profile", Utils.getCurrentUser().getUser_token(), response, errorListener);
    }
}
