package com.example.mvpproject.Model;

import com.example.mvpproject.Resources.ConnectionApi;

import java.util.ArrayList;
import java.util.logging.Logger;

public class PerfilInteractorImplement implements PerfilInteractor {

    private ConnectionApi API_REST = new ConnectionApi();
    private static final Logger logger = Logger.getLogger(HomeInteractorImplement.class.getName());
    private ArrayList<String[]> rows = new ArrayList<>();

    @Override
    public void getDataRequest(String token, boolean is_doctor, HomeInteractor.onRequestListener listener) {

    }
}
