package com.example.mvpproject.Presenter;

import com.example.mvpproject.Model.HomeInteractor;
import com.example.mvpproject.View.HomeView;

import java.util.ArrayList;

public class HomePresenterImplement implements HomePresenter, HomeInteractor.onRequestListener {

    private HomeView homeView;
    private HomeInteractor homeInteractor;

    public HomePresenterImplement(HomeView homeView, HomeInteractor homeInteractor) {
        this.homeView = homeView;
        this.homeInteractor = homeInteractor;
    }

    @Override
    public void onBadRequest() {
        if(homeView != null) {
            homeView.showMessageError();
        }
    }

    @Override
    public void onSucessRequest(ArrayList<String[]> rows, boolean user) {
        if (homeView != null){
            homeView.showTable(rows,user);
        }
    }

    @Override
    public void getInfo(String token, Boolean superUser) {
        homeInteractor.getDataRequest(token,superUser,this);
    }

    @Override
    public void onDestroy() {
        homeView = null;
    }
}
