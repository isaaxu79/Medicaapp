package com.example.mvpproject.Model;

import android.os.Handler;

import com.example.mvpproject.Resources.ConnectionApi;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import cz.msebera.android.httpclient.Header;

public class HomeInteractorImplement implements HomeInteractor{

    private ConnectionApi API_REST = new ConnectionApi();
    private static final Logger logger = Logger.getLogger(HomeInteractorImplement.class.getName());
    private ArrayList<String[]> rows = new ArrayList<>();

    @Override
    public void getDataRequest(final String token, final Boolean supe, final onRequestListener listener) {
        logger.log(Level.SEVERE, "--------------awa de uwu, token : "+token);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(supe){
                    API_REST.getCitas(token, new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody) {
                            try {
                                for(int i = 0; i< responseBody.length();i++){
                                    JSONObject dato = responseBody.getJSONObject(i);
                                    rows.add(new String[]{dato.getString("id"),dato.getString("name"),dato.getString("lastname"),dato.getString("age")});
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            listener.onSucessRequest(rows,supe);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable error, JSONArray responseBody) {

                        }
                    });
                }else{
                    API_REST.getCareers(token,new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody) {
                            try {
                                for(int i = 0; i< responseBody.length();i++){
                                    JSONObject dato = responseBody.getJSONObject(i);
                                    logger.log(Level.SEVERE,"fghjk");
                                    rows.add(new String[]{dato.getString("id"),dato.getString("name"),dato.getString("periodo"),dato.getString("codigo")});
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            listener.onSucessRequest(rows,supe);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable error, JSONArray responseBody) {

                        }
                    });
                }
            }
        },1000);
    }
}
