package com.example.mvpproject.View;

public interface LoginView {

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateTo(String token, String superUser,int id);

    void setLoginError();
}
