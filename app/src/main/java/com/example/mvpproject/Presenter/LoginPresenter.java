package com.example.mvpproject.Presenter;

public interface LoginPresenter {

    void validateCredentials(String username, String password);

    void onDestroy();
}
