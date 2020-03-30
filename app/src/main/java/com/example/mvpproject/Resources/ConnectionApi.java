package com.example.mvpproject.Resources;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ConnectionApi {
    private AsyncHttpClient client = new AsyncHttpClient();
    private final String BASE_URL= "http://192.168.43.135:3333";
    private final String VERSION = "/auth";
    private final String LOGIN = "/login";
    private final String USERS = "/citas";
    private final String CAREERS = "/c/carreras";

    public void login(RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){
        this.client.post(BASE_URL+VERSION+LOGIN,params,asyncHttpResponseHandler);
    }

    public void getCitas(String token, JsonHttpResponseHandler jsonHttpResponseHandler){
        //client.addHeader("Authorization", "token "+token);
        client.get(BASE_URL+USERS+"/3", jsonHttpResponseHandler);
    }

    public void getCareers(String token, JsonHttpResponseHandler jsonHttpResponseHandler){
        //client.addHeader("Authorization", "token "+token);
        client.get(BASE_URL+VERSION+CAREERS,null, jsonHttpResponseHandler);
    }

    public void setHeaders(String token) {
        client.addHeader("Authorization", "token "+token);
    }

    public AsyncHttpClient getClient(){
        return client;
    }
}
