package com.socialnow.Users;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.socialnow.Models.User;

/**
 * Created by mertcan on 2.12.2015.
 */
public class Utils {
    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor loginStateEditor;
    private static User currentUser;

    public static void initialize(Context context){
        if(sharedPref == null || loginStateEditor == null) {
            sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
            loginStateEditor = sharedPref.edit();
        }
        if(getCurrentUserMode())
            retrieveUser();
    }

    public static void cacheUser(User u){
        loginStateEditor.putLong("current_user_id", u.getId());
        loginStateEditor.putString("current_user_name", u.getName());
        loginStateEditor.putString("current_user_email", u.getEmail());
        loginStateEditor.putString("current_user_password", u.getPassword());
        loginStateEditor.putString("current_user_role", u.getRole());
        loginStateEditor.putString("current_user_surname", u.getSurname());
        loginStateEditor.putString("current_user_token", u.getUser_token());


        loginStateEditor.commit();
    }

    public static User retrieveUser(){
        currentUser = new User();
        currentUser.setId(sharedPref.getLong("current_user_id", -1));
        currentUser.setName(sharedPref.getString("current_user_name", ""));
        currentUser.setEmail(sharedPref.getString("current_user_email", ""));
        currentUser.setPassword(sharedPref.getString("current_user_password", ""));
        currentUser.setRole(sharedPref.getString("current_user_role", ""));
        currentUser.setSurname(sharedPref.getString("current_user_surname", ""));
        currentUser.setUser_token(sharedPref.getString("current_user_token", ""));
        currentUser.setUser_token(sharedPref.getString("current_user_photo", ""));
        return currentUser;
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
    public static void logout(){
        cacheUser(new User());
        setCurrentUser(false, null);
    }
}
