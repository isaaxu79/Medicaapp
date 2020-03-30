package com.example.mvpproject.Model;

import java.util.ArrayList;

public interface HomeInteractor {

    interface onRequestListener{

        void onBadRequest();

        void onSucessRequest(ArrayList<String[]> rows, boolean user);
    }

    void getDataRequest(String token, Boolean supe, onRequestListener listener);
}
