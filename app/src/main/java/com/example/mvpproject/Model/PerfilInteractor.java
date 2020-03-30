package com.example.mvpproject.Model;

import java.util.ArrayList;

public interface PerfilInteractor {
    interface onRequestListener{

        void onBadRequest();

        void onSucessRequest(ArrayList<String[]> rows, boolean is_doctor);
    }

    void getDataRequest(String token, boolean is_doctor, HomeInteractor.onRequestListener listener);
}
