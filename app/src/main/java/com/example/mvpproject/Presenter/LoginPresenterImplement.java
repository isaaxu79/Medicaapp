package com.example.mvpproject.Presenter;

import com.example.mvpproject.Model.LoginInteractor;
import com.example.mvpproject.View.LoginView;

public class LoginPresenterImplement implements LoginPresenter, LoginInteractor.OnLoginListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImplement(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentials(String username, String password) {
        if(loginView != null){
            loginView.showProgress();
        }
        loginInteractor.login(username,password,this);
    }

    @Override
    public void onDestroy() {
        loginView = null;  // Este fragmento evita fuga de memoria
    }

    @Override
    public void OnPasswordError() {
        if(loginView != null){
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void OnUsernameError() {
        if(loginView != null){
            loginView.setUsernameError();
            loginView.hideProgress();
        }

    }

    @Override
    public void OnSucess(String token, String superUser,int id) {
        if (loginView != null){
            loginView.navigateTo(token, superUser,id);
            loginView.hideProgress();
        }
    }

    @Override
    public void onError() {
        if(loginView != null) {
            loginView.setLoginError();
            loginView.hideProgress();
        }
    }

}
