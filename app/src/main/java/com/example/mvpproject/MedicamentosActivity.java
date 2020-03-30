package com.example.mvpproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import cz.msebera.android.httpclient.Header;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MedicamentosActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private AsyncHttpClient client = new AsyncHttpClient();
    private String[] header={"id  ","     Nombre      ","   Contenido   ","      Via   "};
    private ArrayList<String[]> rows = new ArrayList<>();
    private TableRow tableRow;
    private TextView txtCell;
    private int indexR;
    private int indexC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
        tableLayout= findViewById(R.id.medicaments);
        getCitas();
        Button cx = findViewById(R.id.button);
        cx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText da = findViewById(R.id.busqueda);
                String val = da.getText().toString();
                boolean ok = busqueda(val);
                if(ok){
                    Toast.makeText(getApplicationContext(),"Si hay existencias",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"No hay existencias",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean busqueda(String val){
        boolean encontre = false;
        for(String[] ad : rows){
            String bd = ad[1].toLowerCase();
            String busq = val.toLowerCase();
            if(bd.equals(busq)){
                encontre = true;
            }
        }
        return encontre;
    }

    private void getCitas() {
        client.get(getApplicationContext(), "http://192.168.43.135:3333" + "/medicame", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody) {
                //logger.log(Level.INFO,"------------"+statusCode);
                try {
                    for(int i = 0; i< responseBody.length();i++){
                        JSONObject dato = responseBody.getJSONObject(i);
                        //logger.log(Level.SEVERE,"fghjk");
                        rows.add(new String[]{dato.getString("id"),dato.getString("nombre_comercial"),dato.getString("presentacion")+" de "+dato.getString("contenido"),dato.getString("via_suministrado")});
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                createHeader();
                createDataTable();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error, JSONArray responseBody) {
                //logger.log(Level.INFO,"------------"+statusCode);
                switch (statusCode){
                    case 0:
                        Toast.makeText(getApplicationContext(),"Tiempo de espera terminado",Toast.LENGTH_LONG).show();
                    case 404:
                        Toast.makeText(getApplicationContext(),"Error de envio de datos o datos incorrectos",Toast.LENGTH_LONG).show();
                        break;
                    case 400:
                        Toast.makeText(getApplicationContext(),"Ha surgido un error interno",Toast.LENGTH_LONG).show();
                        break;
                    case 500:
                        Toast.makeText(getApplicationContext(),"Problemas de comunicacion",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    private void newRow(){
        tableRow = new TableRow(getApplicationContext());
    }

    private void newCell(){
        txtCell = new TextView(getApplicationContext());
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(15);
    }

    private void createHeader(){
        indexC = 0;
        newRow();
        while (indexC < header.length){
            newCell();
            txtCell.setText(header[indexC++]);
            tableRow.addView(txtCell, newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }

    private void createDataTable(){
        String info;
        for(indexR=1;indexR<rows.size();indexR++){
            newRow();
            for(indexC=0;indexC<header.length;indexC++){
                newCell();
                String[] columns= rows.get(indexR-1);
                info=(indexC<columns.length)?columns[indexC]:"";
                txtCell.setText(info);
                tableRow.addView(txtCell, newTableRowParams());
            }

            tableLayout.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight=1;
        return params;
    }
}
