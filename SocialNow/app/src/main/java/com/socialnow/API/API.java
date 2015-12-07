package com.socialnow.API;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.socialnow.App;
import com.socialnow.Models.AccessToken;
import com.socialnow.Models.Event;
import com.socialnow.Models.Group;
import com.socialnow.Models.User;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mugekurtipek on 24/11/15.
 */
public class API {
    private static String MAIN_URL = "http://ec2-52-11-176-49.us-west-2.compute.amazonaws.com:8080/social_backend/";
//    private static String MAIN_URL = "http://10.0.3.2:8080/";//To try local database via genymotion emulator
    private static RequestQueue mQueue;
    private static API instance;
    private static String UUID;

    public static void init() {
        if (instance == null) {
            instance = new API();
            mQueue = Volley.newRequestQueue(App.getInstance());
        }
    }

    public static void setUUID() {
        instance.UUID = UUID;
    }
    public static void setUUID(String UUID) {
        instance.UUID = UUID;
    }

    public static void cancelRequestByTag(final String tag) {
        mQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return request.getTag().equals(tag);
            }
        });
    }
    public static void login(String tag, User user, Response.Listener<User> successListener,
                             Response.ErrorListener failureListener) {
        String postBody = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return !(f.getName().equals("email") || f.getName().equals("password"));
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create().toJson(user, User.class);
        System.out.println(postBody);
        mQueue.add(new GeneralRequest<>(Request.Method.POST,
                MAIN_URL + "/login", User.class, successListener, failureListener)
                .setPostBodyInJSONForm(postBody).setTag(tag));
    }

    public static void signin(String tag, User user, Response.Listener<User> successListener,
                             Response.ErrorListener failureListener) {
        String postBody = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return !(f.getName().equals("email") || f.getName().equals("password"));
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create().toJson(user, User.class);
        System.out.println(postBody);
        mQueue.add(new GeneralRequest<>(Request.Method.POST,
                MAIN_URL + "/signUp", User.class, successListener, failureListener)
                .setPostBodyInJSONForm(postBody).setTag(tag));
    }
    public static void listAllEvents(String tag, Response.Listener<Event[]> successListener,
                                                                          Response.ErrorListener failureListener) {
                mQueue.add(new GeneralRequest<>(Request.Method.POST, MAIN_URL + "/listAllEvents",
                              Event[].class, successListener, failureListener));
          }

    public static void listAllGroups(String tag, Response.Listener<Group[]> successListener,
                                     Response.ErrorListener failureListener) {
        String postBody = "{}";
        mQueue.add(new GeneralRequest<>(Request.Method.POST, MAIN_URL + "/listAllGroups",
                Group[].class, successListener, failureListener).setPostBodyInJSONForm(postBody));
    }

    public static void createEvent(String tag, Event e, Response.Listener<Event> successListener,
                                     Response.ErrorListener failureListener) {
        String postBody = new Gson().toJson(e);
        mQueue.add(new GeneralRequest<>(Request.Method.POST, MAIN_URL + "/createEvent",
                Event.class, successListener, failureListener)
                .setPostBodyInJSONForm(postBody).setTag(tag));
    }

    private static class GeneralRequest<T> extends Request<T> {

        private static final String PROTOCOL_CHARSET = "utf-8";
        private static final String PROTOCOL_CONTENT_TYPE =
                String.format("application/json; charset=%s", PROTOCOL_CHARSET);

        private Gson gson = new GsonBuilder()
                .setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
        private Class<T> responseClazz;
        private Map<String, String> headers = new HashMap<>();
        private Response.Listener<T> listener;

        private String postBody;

        public GeneralRequest(int method, String url, Class<T> responseClazz,
                              Response.Listener<T> listener, Response.ErrorListener errorListener) {
            super(method, url, errorListener);
            this.responseClazz = responseClazz;
            this.listener = listener;

            Log.d((String) getTag(), url + ", method: " +
                    (method == Request.Method.GET ? "GET" : "POST"));
        }

        public GeneralRequest<T> setPostBodyInJSONForm(Object postObject,
                                                       Class<? extends Object> postClass) {
            this.postBody = gson.toJson(postObject, postClass);
            Log.v("Request", postBody);
            return this;
        }

        public GeneralRequest<T> setPostBodyInJSONForm(String postBody) {
            this.postBody = postBody;
            Log.v("Request", postBody);
//            this.postBody = "{email:erdem@gmail.com,password:123456}";
            return this;
        }

        public GeneralRequest<T> setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = this.headers != null ?
                    this.headers : super.getHeaders();
            if (UUID != null)
                headers.put("Authorization", "Bearer " + UUID);
            return headers;
        }

        @Override
        protected void deliverResponse(T response) {
            Log.v("Response", new Gson().toJson(response));
            if (listener != null)
                listener.onResponse(response);
        }


        @Override
        public byte[] getBody() throws AuthFailureError {
            try {
                return postBody == null ? null : postBody.getBytes(PROTOCOL_CHARSET);
            } catch (UnsupportedEncodingException uee) {
                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                        postBody, PROTOCOL_CHARSET);
                return null;
            }
        }

        @Override
        public String getBodyContentType() {
            return postBody == null ? null : PROTOCOL_CONTENT_TYPE;
        }

        @Override
        protected Response<T> parseNetworkResponse(NetworkResponse response) {
            try {

                String json = new String(
                        response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                Log.v("Request", json);
                return Response.success(
                        responseClazz.equals(Void.class) ? null :
                                gson.fromJson(json, responseClazz),
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JsonSyntaxException e) {
                return Response.error(new ParseError(e));
            }
        }
    }
}
