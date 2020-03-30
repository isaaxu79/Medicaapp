package com.example.mvpproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvpproject.Model.LoginInteractorImplement;
import com.example.mvpproject.Presenter.LoginPresenter;
import com.example.mvpproject.Presenter.LoginPresenterImplement;
import com.example.mvpproject.View.LoginView;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private EditText usuario;
    private EditText contrasena;
    private ProgressBar progressBar;
    private LoginPresenter presenter;
    private static final Logger logger = Logger.getLogger(LoginActivity.class.getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = findViewById(R.id.progressBar);
        usuario = findViewById(R.id.username);
        contrasena = findViewById(R.id.password);
        findViewById(R.id.logIn).setBackgroundColor(Color.parseColor("#00695C"));
        Button x = findViewById(R.id.logIn);
        x.setTextColor(Color.parseColor("#FFFFFF"));
        findViewById(R.id.logIn).setOnClickListener(this);

        presenter = new LoginPresenterImplement(this, new LoginInteractorImplement());
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        presenter.validateCredentials(usuario.getText().toString(),contrasena.getText().toString());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        usuario.setError("Usuario Incorrecto");
    }

    @Override
    public void setPasswordError() {
        contrasena.setError("Contraseña Incorrecta");
    }

    @Override
    public void navigateTo(String token, String superUser, int id) {
        Toast.makeText(this,"Pase UwU",Toast.LENGTH_LONG).show();
        logger.log(Level.SEVERE,"TOKEN: "+token+" Super: "+ superUser);
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        intent.putExtra("token",token);
        logger.log(Level.SEVERE,String.valueOf(id));
        intent.putExtra("idx",String.valueOf(id));
        startActivity(intent);
    }

    @Override
    public void setLoginError() {
        Toast.makeText(this,"Usuario no existe",Toast.LENGTH_LONG).show();
    }
}
