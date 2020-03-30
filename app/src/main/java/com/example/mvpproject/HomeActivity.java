package com.example.mvpproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpproject.Model.HomeInteractorImplement;
import com.example.mvpproject.Presenter.HomePresenter;
import com.example.mvpproject.Presenter.HomePresenterImplement;
import com.example.mvpproject.View.HomeView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements HomeView {

    private HomePresenter presenter;
    private TableLayout tableLayouts;
    String[] headers ={"id","name","lastname","age",""};
    private ArrayList<String[]> rowss = new ArrayList<>();
    private TableRow tableRows;
    private TextView txtCells;
    private int indexRs;
    private int indexCs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        System.out.println(getIntent().getStringExtra("idx"));
        CardView asx = findViewById(R.id.perf);
        asx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PerfilActivity.class);
                intent.putExtra("id",getIntent().getStringExtra("idx"));
                startActivity(intent);
            }
        });

        CardView citas = findViewById(R.id.citas);
        citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MisCitasActivity.class);
                startActivity(intent);
            }
        });

        CardView medicina = findViewById(R.id.medica);
        medicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MedicamentosActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showTable(ArrayList<String[]> rows, boolean user) {

    }

    @Override
    public void showMessageError() {
        Toast.makeText(this,"Error con el servioio, contacte a soporte",Toast.LENGTH_LONG).show();
    }
}
