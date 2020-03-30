package com.example.mvpproject.Presenter;

public interface HomePresenter {

    void getInfo(String token, Boolean superUser);

    void onDestroy();
}
