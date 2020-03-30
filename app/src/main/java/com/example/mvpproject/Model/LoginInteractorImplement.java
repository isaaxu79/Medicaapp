package com.example.mvpproject.Model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.mvpproject.Resources.ConnectionApi;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import cz.msebera.android.httpclient.Header;

public class LoginInteractorImplement implements LoginInteractor {

    private ConnectionApi API_REST = new ConnectionApi();
    private static final Logger logger = Logger.getLogger(LoginInteractorImplement.class.getName());

    @Override
    public void login(final String username, final String password, final OnLoginListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(username)){
                    listener.OnUsernameError();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    listener.OnPasswordError();
                    return;
                }
                RequestParams params = new RequestParams();
                params.put("nss",username);
                params.put("password",password);
                API_REST.login(params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try{
                            JSONObject object = new JSONObject(new String(responseBody));
                            int id =  Integer.parseInt(object.getString("user"));
                            String tkn = object.getString("token");
                            listener.OnSucess(tkn,"true",id);
                        }catch (Exception e){}
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        listener.onError();
                    }
                });
            }
        },1000);
    }


}
